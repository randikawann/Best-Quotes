package com.xiteb.mortivationalquotes.model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("albamId")
    private int albamId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;


    public Quote(int albamId, int id, String title, String url, String thumbnailUrl) {
        this.albamId = albamId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Quote(){

    }

    public int getAlbamId() {
        return albamId;
    }

    public void setAlbamId(int albamId) {
        this.albamId = albamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
