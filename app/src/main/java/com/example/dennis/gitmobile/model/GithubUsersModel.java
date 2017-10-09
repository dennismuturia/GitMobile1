package com.example.dennis.gitmobile.model;

/**
 * Created by dennis on 10/5/17.
 */

public class GithubUsersModel {
    private String userName;
    private String profPic;
    private String user;
    private String repos;
    private String theUser;
    private String repoUrl;

    public GithubUsersModel(String userName, String profPic, String user, String repos, String theUser, String repoUrl){
        this.userName = userName;
        this.profPic = profPic;
        this.user = user;
        this.repos = repos;
        this.theUser = theUser;
        this.repoUrl = repoUrl;
    }

    public String getProfPic() {
        return profPic;
    }

    public String getRepos() {
        return repos;
    }

    public String getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public String getTheUser(){ return theUser;}

    public String getRepoUrl(){ return repoUrl;}
}
