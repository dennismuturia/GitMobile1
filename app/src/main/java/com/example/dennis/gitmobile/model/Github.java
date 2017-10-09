package com.example.dennis.gitmobile.model;

/**
 * Created by dennis on 10/3/17.
 */

public class Github {
    private String userName;
    private String followers;
    private String following;
    private String bio;
    private String profilePic;
    private String hireable;
    private String publicRepos;
    private String privateRepos;
    private String name;
    private String public_repos;


    public Github(String userName, String bio, String profilePic,String name ,String hireable,
                  String publicRepos, String privateRepos, String followers,
                  String following, String public_repos){
        this.userName = userName;
        this.followers = followers;
        this.name = name;
        this.following = following;
        this.profilePic = profilePic;
        this.publicRepos = publicRepos;
        this.hireable = hireable;
        this.bio = bio;
        this.privateRepos = privateRepos;
        this.public_repos = public_repos;
    }

    public String getUserName(){
        return userName;
    }
    public String getFollowers(){return followers;}
    public String getName(){return name;}
    public String getBio(){return bio;}
    public String getFollowing(){return following;}
    public String getProfilePic(){return profilePic;}
    public String getHireable(){return hireable;}
    public String getPublicRepos(){return publicRepos;}
    public String getPrivateRepos(){return privateRepos;}
    public String getPublic_repos(){ return public_repos;}
}

