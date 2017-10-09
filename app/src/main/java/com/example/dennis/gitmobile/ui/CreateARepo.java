package com.example.dennis.gitmobile.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dennis.gitmobile.R;
import com.example.dennis.gitmobile.service.CreateARepoService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateARepo extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.createARepo)Button mButton;
    @Bind(R.id.repositoryName) EditText mRepoName;
    @Bind(R.id.repoDescription)EditText mDescription;
    @Bind(R.id.privacy)EditText mPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_arepo);

        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mButton){
           String name  = mRepoName.getText().toString().trim();
            String description = mDescription.getText().toString().trim();
            String privacy = mPrivacy.getText().toString().trim();

            try {
                postNewRepo(name, description, privacy);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void postNewRepo(String name, String description, String privacy) throws IOException {
        final CreateARepoService createARepoService = new CreateARepoService();
        createARepoService.createARepo(name, description, privacy, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
