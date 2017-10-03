package com.example.dennis.gitmobile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dennis on 10/3/17.
 */

public class GitService {
    public static void myUser(Callback callback) {
        //Due to the OAuth 2 authentication we will need to add the following
        /*
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_SECRET_TOKEN);
        consumer.setTokenWithSecret(Constants.GITHUB_ACCESS_TOKEN_NAME, Constants.GITHUB_ACCESS_TOKEN);
*/
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GITHUB_BASE_URL).newBuilder();

        urlBuilder.addQueryParameter(Constants.GITHUB_ACCESS_TOKEN_NAME, Constants.GITHUB_ACCESS_TOKEN);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENTID_NAME, Constants.GITHUB_CLIENT_ID);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENT_SECRET_NAME, Constants.GITHUB_SECRET_TOKEN);

        //Now we build this url into a string
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //Now why dont we fetch the data itself and display it to the users?
    public static ArrayList<Github> processResults(Response response){
        ArrayList<Github> githubs = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject gitJSON = new JSONObject(jsonData);
                String userName = gitJSON.getString("login");
                String bio = gitJSON.getString("bio");
                String profPic = gitJSON.getString("avatar_url");
                String name = gitJSON.getString("name");
                String hireable = gitJSON.getString("hireable");
                String publicRepos = gitJSON.getString("public_repos");
                String privateRepos = gitJSON.getString("total_private_repos");
                String followers = gitJSON.getString("followers");
                String following = gitJSON.getString("following");
                Github github = new Github(userName,bio,profPic,name,hireable,publicRepos,privateRepos,followers,following);
                githubs.add(github);
                //Log.v("BIO", bio);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return githubs;
    }

}

