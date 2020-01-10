package com.zipcode.socialStream.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private Long videoId;
    private String comment;
    private String reply = null;
    private Long userId;
    private Date createDate;

    public Comment(Long videoId, String comment, String reply, Long userId) {
        this.videoId = videoId;
        this.comment = comment;
        this.userId = userId;
        this.createDate = createDate;
    }

    public Comment(Long commentId, Long videoId, String comment, String reply, Long userId, Date createDate) {
        this.videoId = videoId;
        this.comment = comment;
        this.userId = userId;
        this.createDate = createDate;
    }

    // Nullary constructor
    public Comment(){

    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

