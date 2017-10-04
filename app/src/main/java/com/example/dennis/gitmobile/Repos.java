package com.example.dennis.gitmobile;

/**
 * Created by dennis on 10/3/17.
 */
//THis will be my repomodel that will be holding the data

public class Repos {
    private String projectName;
    private String watchers;
    private String forks;
    private String language;
    private String profPic;

    public Repos(String projectName, String watchers, String forks, String language,String profPic){
        this.projectName = projectName;
        this.watchers = watchers;
        this.forks = forks;
        this.language = language;
        this.profPic = profPic;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getWatchers(){
        return watchers;
    }

    public String getForks() {
        return forks;
    }

    public String getLanguage() {
        return language;
    }

    public String getProfPic() {
        return profPic;
    }
}
