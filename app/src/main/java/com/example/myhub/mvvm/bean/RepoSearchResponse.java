package com.example.myhub.mvvm.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    private int total;

    @SerializedName("items")
    private ArrayList<Repo> items = new ArrayList<>();

    private int nextPage;

    public ArrayList<Repo> getItems() {
        return items;
    }



}