package com.zipcode.socialStream.repositories;

import com.zipcode.socialStream.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> getAllByVideoId(Long videoId);
    List<Comment> getAllByCommentId(Long commentId);
    List<Comment> getAllByUserId(Long userId);

}
