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
 * Created by dennis on 10/4/17.
 */

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.RepoViewHolder>{
    private ArrayList<Repos> mRepos = new ArrayList<>();
    private Context mContext;

    public ReposListAdapter(Context context, ArrayList<Repos> repos){
        mContext = context;
        mRepos = repos;
    }

    @Override
    public ReposListAdapter.RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_user_profile, parent, false);
        ReposListAdapter.RepoViewHolder viewHolder = new ReposListAdapter.RepoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReposListAdapter.RepoViewHolder holder, int position) {
        holder.bindRepos(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }
    public class RepoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.myImage)
        ImageView mRepoImageView;
        @Bind(R.id.repoName)
        TextView mRepoName;
        @Bind(R.id.myDescription) TextView mDescription;
        @Bind(R.id.myLanguage) TextView mLanguage ;
        @Bind(R.id.myWatchers)TextView mWatchers;
        @Bind(R.id.myforks) TextView mForks;
        private Context mContext;


        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRepos(Repos repos) {
            Picasso.with(mContext).load(repos.getProfPic()).into(mRepoImageView);
            mRepoName.setText("Project Name: " + repos.getProjectName());
            mDescription.setText("Description: "+ repos.getDescription());
            mLanguage.setText("Language: "+ repos.getLanguage());
            mWatchers.setText("Watchers: " + repos.getWatchers());
            mForks.setText("Forks: "+repos.getForks());
        }
    }
}
