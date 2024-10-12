package com.skillspace.authservice.models;

public class Profile {
    private String name;
    private String branch;
    private String bio;
    private String profile_image_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", bio='" + bio + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                '}';
    }
}
