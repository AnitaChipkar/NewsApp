package com.anita.pricify.newsapp.newschannel.model;

import java.io.Serializable;

/**
 * Created by anitachipkar on 19/09/17.
 */

public class NewsModel implements Serializable{
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public NewsModel(String author, String title, String publishedAt , String description , String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.publishedAt = publishedAt;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
