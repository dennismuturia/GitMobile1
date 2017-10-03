package com.example.dennis.gitmobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = MainActivity.class.getSimpleName();
    //@Bind(R.id.myList)ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private GithubListAdapter mAdapter;

    public ArrayList<Github> mGithub = new ArrayList<>();
    GitService gitService = new GitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GitService.myUser(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.wtf("FAILED", "hAS fAILED");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // String jsonData = response.body().string();
                //  Log.v(TAG, jsonData);
                mGithub = GitService.processResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new GithubListAdapter(getApplicationContext(), mGithub);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MainActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
