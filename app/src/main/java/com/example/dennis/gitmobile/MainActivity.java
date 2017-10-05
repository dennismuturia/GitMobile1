package com.example.dennis.gitmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    //@Bind(R.id.myList)ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.recyclerView1)RecyclerView belowRecycler;
    @Bind(R.id.btn) Button mButton;
   // @Bind(R.id.searchButton) Button searchButton;
    private GithubListAdapter mAdapter;
    private ReposListAdapter mAdapter1;

    public ArrayList<Github> mGithub = new ArrayList<>();
    public  ArrayList<Repos> mRepos = new ArrayList<>();
    RepoService repoService = new RepoService();
    GitService gitService = new GitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButton.setOnClickListener(this);
        GitService.myUser(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.wtf("FAILED", "hAS fAILED");

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
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

        RepoService.myRepos(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.wtf("FAILED", "hAS fAILED");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRepos = RepoService.processReposResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter1 = new ReposListAdapter(getApplicationContext(), mRepos);
                        belowRecycler.setAdapter(mAdapter1);
                        RecyclerView.LayoutManager layoutManager1 =
                                new LinearLayoutManager(MainActivity.this);
                        belowRecycler.setLayoutManager(layoutManager1);
                        belowRecycler.setHasFixedSize(false);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            Intent intent = new Intent(this, CreateARepo.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.searchRepos);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, GithubUsers.class);
                intent.putExtra(String.valueOf(searchView), "User");
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        menuInflater.inflate(R.menu.main, menu);
        return  super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchRepos) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
