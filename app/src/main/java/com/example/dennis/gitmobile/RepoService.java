package com.example.dennis.gitmobile;

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
 * Created by dennis on 10/3/17.
 */

public class RepoService {
    public static void myRepos(Callback callback){
        //Same as the GitService we will be building the url
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GITHUB_REPOS_URL).newBuilder();

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
    //Now why don't we display data on the mobile phone?? Here we go
    public static ArrayList<Repos>processReposResults(Response response){
        ArrayList<Repos> reposes = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONArray gitArray = new JSONArray(jsonData);
                for (int i = 0; i < gitArray.length(); i++){
                    JSONObject gitObject =gitArray.getJSONObject(i);
                    String projectName = gitObject.getString("name");
                    String watchers = gitObject.getString("watchers");
                    String forks = gitObject.getString("forks_count");
                    String language = gitObject.getString("language");
                    String description = gitObject.getString("description");
                    String projectUrl = gitObject.getString("html_url");
                    JSONObject ownerObject = gitObject.getJSONObject("owner");
                    String profpic = ownerObject.getString("avatar_url");

                    Repos repos = new Repos(projectName, watchers, forks, language, profpic, description, projectUrl);
                    reposes.add(repos);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reposes;
    }
}
