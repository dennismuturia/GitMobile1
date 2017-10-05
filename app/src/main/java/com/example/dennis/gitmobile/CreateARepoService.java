package com.example.dennis.gitmobile;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dennis on 10/4/17.
 */

public class CreateARepoService {
    /*
    This will be used to post data to github
     */

    public static void createARepo(String name, String description, String privacy, Callback callback) throws IOException {
        //This will basically create a repository builder. Where i will need to create
        OkHttpClient client = new OkHttpClient.Builder().build();
                //.addInterceptor(new SigningInterceptor(consumer))

        //Lets build a body to add to the github
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("description", description)
                .add("private", privacy)
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GITHUB_REPOS_URL).newBuilder();

        urlBuilder.addQueryParameter(Constants.GITHUB_ACCESS_TOKEN_NAME, Constants.GITHUB_ACCESS_TOKEN);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENTID_NAME, Constants.GITHUB_CLIENT_ID);
        urlBuilder.addQueryParameter(Constants.GITHUB_CLIENT_SECRET_NAME, Constants.GITHUB_SECRET_TOKEN);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url("url")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
        Log.d(url, "myUrl");

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
    }
    public void processResults(Response response){

    }
}
