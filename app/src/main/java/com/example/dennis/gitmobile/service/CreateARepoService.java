package com.example.dennis.gitmobile.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.dennis.gitmobile.Constants;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.description;
import static android.R.attr.duration;
import static android.R.attr.name;

/**
 * Created by dennis on 10/4/17.
 */

public class CreateARepoService  extends AsyncTask<String, Void, String> {
    /*
    This will be used to post data to github
     */

    public static void createARepo(String name, String description, String privacy, Callback callback) throws IOException

    {
        /*

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
        */
    }
    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL(Constants.GITHUB_REPOS_URL);
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("name", name);
            postDataParams.put("description", description);
            postDataParams.put("private", false);
            Log.e("params",postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {

    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
