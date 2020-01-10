package com.zipcode.socialStream.controllers;

import com.zipcode.socialStream.models.Comment;
import com.zipcode.socialStream.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {

    private CommentRepository repository;

    @Autowired
    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public Comment addCommentToVideo(Comment comment, Long videoId, Long userId) {
        Comment comment1 = repository.save(comment);
        comment1.setVideoId(videoId);
        comment1.setUserId(userId);
        comment1.setCreateDate(date);

        return comment1;
    }
}