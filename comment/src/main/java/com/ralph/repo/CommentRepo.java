package com.ralph.repo;

import com.ralph.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
    List<Comment> findAllByMediaId(Long Id);

    void deleteAllByMediaId(Long mediaId);
}
