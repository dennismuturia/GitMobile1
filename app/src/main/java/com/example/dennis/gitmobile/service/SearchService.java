package com.example.dennis.gitmobile.service;

import com.example.dennis.gitmobile.Constants;
import com.example.dennis.gitmobile.model.GithubUsersModel;

import org.json.JSONArray;
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
 * Created by dennis on 10/5/17.
 */

public class SearchService {
    public static void users(String user, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GITHUB_SEARCH_USERS_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GITHUB_SEARCH_QUERY, user);
        urlBuilder.addQueryParameter("?"+Constants.GITHUB_ACCESS_TOKEN_NAME, Constants.GITHUB_ACCESS_TOKEN);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENTID_NAME, Constants.GITHUB_CLIENT_ID);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENT_SECRET_NAME, Constants.GITHUB_SECRET_TOKEN);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static ArrayList<GithubUsersModel> processUsersResults(Response response){
        ArrayList<GithubUsersModel> githubUserseModels = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject userObject = new JSONObject(jsonData);
                JSONArray userArray = userObject.getJSONArray("items");
                for (int i = 0; i < userArray.length(); i++){
                    JSONObject thisObject = userArray.getJSONObject(i);
                    String userName = thisObject.getString("login");
                    String profPic = thisObject.getString("avatar_url");
                    String user = thisObject.getString("html_url");
                    String repos = thisObject.getString("repos_url");
                    String theUser = thisObject.getString("html_url");
                    String repoUrl = thisObject.getString("repos_url");

                    GithubUsersModel githubUsersModel = new GithubUsersModel(userName, profPic,user,repos, theUser,repoUrl);
                    githubUserseModels.add(githubUsersModel);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return githubUserseModels;
    }


}
