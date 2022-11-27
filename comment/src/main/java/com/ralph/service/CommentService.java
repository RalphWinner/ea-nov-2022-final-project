package com.ralph.service;

import com.ralph.dto.CommentDTO;
import com.ralph.entity.Comment;

import java.util.List;
import java.util.function.LongFunction;

public interface CommentService {
    List<CommentDTO> findAll();
    String save(Comment comment, Long mediaId);
    String delete(Long Id);
    List<CommentDTO> findAllById(Long id);
    String deleteAllByMediaId(Long Id);
    String update(Long Id, Comment comment);
}
