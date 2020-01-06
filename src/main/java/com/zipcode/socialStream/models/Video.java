package com.zipcode.socialStream.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;
    private String videoName;
    private String videoDescription;
    private String location;
    private Long userId;

    // Nullary Constructor
    public Video(){

    }

    public Video(Long videoId, String videoName, String videoDescription, Long userId) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.userId = userId;
    }

    public Video(String videoName, String videoDescription, Long userId) {
        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
