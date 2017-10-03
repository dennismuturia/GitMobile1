package com.example.dennis.gitmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dennis on 10/3/17.
 */

public class GithubListAdapter extends RecyclerView.Adapter<GithubListAdapter.GithubViewHolder> {
    private ArrayList<Github> mGithub = new ArrayList<>();
    private Context mContext;

    public GithubListAdapter(Context context, ArrayList<Github> githubs){
        mContext = context;
        mGithub = githubs;
    }

    @Override
    public GithubListAdapter.GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile, parent, false);
       GithubViewHolder viewHolder = new GithubViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GithubListAdapter.GithubViewHolder holder, int position) {
        holder.bindGithub(mGithub.get(position));
    }

    @Override
    public int getItemCount() {
        return mGithub.size();
    }


    public class GithubViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView) ImageView mGitImageView;
        @Bind(R.id.userName) TextView mNameTextView;
        @Bind(R.id.followers) TextView mFollowersTextView;
        @Bind(R.id.following) TextView mFollowing ;
        @Bind(R.id.myName)TextView mUserName;
        @Bind(R.id.myBio) TextView mBio;
        private Context mContext;


        public GithubViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGithub(Github github) {
            Picasso.with(mContext).load(github.getProfilePic()).into(mGitImageView);
            mNameTextView.setText(github.getName());
            mFollowersTextView.setText("Followers "+ github.getFollowing());
            mFollowing.setText("Following "+ github.getFollowing());
            mUserName.setText(github.getUserName());
            mBio.setText("Bio "+github.getBio());
        }
    }
}
