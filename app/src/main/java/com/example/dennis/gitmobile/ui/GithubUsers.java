package com.example.dennis.gitmobile.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dennis.gitmobile.R;
import com.example.dennis.gitmobile.adapter.GithubUsersListAdapter;
import com.example.dennis.gitmobile.model.GithubUsersModel;
import com.example.dennis.gitmobile.service.SearchService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GithubUsers extends AppCompatActivity {
    @Bind(R.id.myRecyclerView)RecyclerView thisRecyclerView;

    private GithubUsersListAdapter mAdapter;

    public ArrayList<GithubUsersModel> mGithubUses = new ArrayList<>();

    SearchService mySearch = new SearchService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_users);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String user = intent.getStringExtra("User");

        getName(user);

    }
    public void getName(String user){
        final SearchService sv = new SearchService();
        sv.users(user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.wtf("FAILED", "hAS fAILED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mGithubUses = SearchService.processUsersResults(response);
                GithubUsers.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new GithubUsersListAdapter(getApplicationContext(), mGithubUses);
                        thisRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(GithubUsers.this);
                        thisRecyclerView.setLayoutManager(layoutManager);
                        thisRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
