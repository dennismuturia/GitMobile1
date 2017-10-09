package com.example.dennis.gitmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dennis.gitmobile.R;
import com.example.dennis.gitmobile.model.GithubUsersModel;
import com.example.dennis.gitmobile.ui.GithubUsers;
import com.example.dennis.gitmobile.ui.MainActivity;
import com.example.dennis.gitmobile.ui.PersonUserActivity;
import com.example.dennis.gitmobile.ui.RepoUrlActivity;
import com.example.dennis.gitmobile.ui.RepoWebViewActivity;
import com.example.dennis.gitmobile.ui.ViewUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dennis on 10/5/17.
 */

public class GithubUsersListAdapter extends RecyclerView.Adapter<GithubUsersListAdapter.GithubUsersViewHolder> {
    private ArrayList<GithubUsersModel> mGithub = new ArrayList<>();
    private Context mContext;

    public GithubUsersListAdapter(Context context, ArrayList<GithubUsersModel> githubs){
        mContext = context;
        mGithub = githubs;
    }
    @Override
    public GithubUsersListAdapter.GithubUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.the_users, parent, false);
        GithubUsersListAdapter.GithubUsersViewHolder viewHolder = new GithubUsersListAdapter.GithubUsersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GithubUsersListAdapter.GithubUsersViewHolder holder, int position) {
        holder.bindGithub(mGithub.get(position));
    }

    @Override
    public int getItemCount() {
        return mGithub.size();
    }


    public class GithubUsersViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.searchUserImage)ImageView myImage;
        @Bind(R.id.searchUserName)TextView myUserName;
        @Bind(R.id.searchUserRepos)Button userRepos;
        @Bind(R.id.searchUserUrl)Button userUrl;
        private Context mContext;


        public GithubUsersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGithub(final GithubUsersModel githubModel) {
            Picasso.with(mContext).load(githubModel.getProfPic()).into(myImage);
            myUserName.setText(githubModel.getUserName());
            final String personUrl = githubModel.getTheUser();
            final String repoUrl = githubModel.getRepoUrl();
            /*
            userRepos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, RepoUrlActivity.class);
                    intent.putExtra("repoUrl", repoUrl);
                    mContext.startActivity(intent);
                }
            });
*/
            userUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PersonUserActivity.class);
                    intent.putExtra("personUrl", personUrl);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
