package com.example.myhub.mvvm.bean;

import com.example.base.recyclerview_databinding.adapter.listadapter.IDiffUtil;
import com.google.gson.annotations.SerializedName;

/**
 * Time:2019/11/9 11:36
 * Author: han1254
 * Email: 1254763408@qq.com
 */
public class Repo implements IDiffUtil<Repo> {
    @SerializedName("id")
    public Long id;
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("description")
    public String description;
    @SerializedName("html_url")
    public String url;
    @SerializedName("stargazers_count")
    public int stars;
    @SerializedName("forks_count")
    public int forks;
    @SerializedName("owner")
    public RepoOwner owner;

    private String star_str;

    public RepoOwner getOwner() {
        return owner;
    }

    public void setOwner(RepoOwner owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getStars() {
        return stars;
    }

    public String getStar_str() {
        return String.valueOf(stars);
    }

    public int getForks() {
        return forks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    @Override
    public boolean areItemSame(Repo repo) {
        return repo.getId().equals(this.id);
    }

    @Override
    public boolean areContentSame(Repo repo) {
        return repo == this;
    }
}
