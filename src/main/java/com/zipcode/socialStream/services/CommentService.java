package com.zipcode.socialStream.services;

import com.zipcode.socialStream.models.Comment;
import com.zipcode.socialStream.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public Comment saveComment(Comment comment){
        return repository.save(comment);
    }
    public Comment addCommentToVideo(Comment comment, Long videoId, Long userId) {
        Comment comment1 = repository.save(comment);
        comment1.setVideoId(videoId);
        comment1.setUserId(userId);
        comment1.setCreateDate(date);

        return comment1;
    }
    public Comment addReplyToComment(Comment comment,String reply, Long videoId, Long userId) {
        Comment comment1 = repository.save(comment);
        comment1.setVideoId(videoId);
        comment1.setUserId(userId);
        comment1.setReply(reply);
        comment1.setCreateDate(date);

        return comment1;
    }
    List<Comment> getAllByVideoId(Long videoId){
        return repository.getAllByVideoId(videoId);
    }
    List<Comment> getAllByCommentId (Long commentId){
        return repository.getAllByCommentId(commentId);
    }
    List<Comment> getAllByUserId(Long userId) {return repository.getAllByUserId(userId);}
}