package com.example.dennis.gitmobile.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.dennis.gitmobile.R;

public class RepoUrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_url);

        WebView webView = new WebView(this);
        setContentView(webView);


        Intent intent = getIntent();
        String url = intent.getStringExtra("repoUrl");
        webView.loadUrl(url);
    }
}
