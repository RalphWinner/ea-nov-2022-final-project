package com.ralph.service.impl;

import com.ralph.dto.CommentDTO;
import com.ralph.dto.Converter;
import com.ralph.entity.Comment;
import com.ralph.repo.CommentRepo;
import com.ralph.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private Converter converter;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CommentDTO> findAll() {
        return commentRepo.findAll().stream()
                .filter(comment -> comment.isDelete() == false)
                .map(comment -> converter.commentToDTO(comment))
                .toList();
    }

    @Override
    public String save(Comment comment, Long mediaId) {
        comment.setMediaId(mediaId);
        try{
            commentRepo.save(comment);
            return "Comment added successfully";
        }catch (Exception exception){
            return "Comment not saved ->" + exception.getCause();
        }
    }

    @Override
    public String delete(Long Id) {
        try{
            Comment comment = commentRepo.findById(Id).get();
            comment.setDelete(true);
            comment.setDeleteDateTime(LocalDateTime.now());
            commentRepo.save(comment);
            return "Comment deleted successfully";
        }catch (Exception exception){
            return "Error,, comment not deleted -> " + exception.getCause();
        }
    }

    @Override
    public List<CommentDTO> findAllById(Long mediaId) {
        return commentRepo.findAllByMediaId(mediaId).stream()
                .filter(comment -> comment.isDelete() == false)
                .map(comment -> converter.commentToDTO(comment))
                .toList();
    }

    @Override
    public String deleteAllByMediaId(Long Id) {
        commentRepo.deleteAllByMediaId(Id);
        return "All comments deleted successfully";
    }

    @Override
    public String update(Long Id, Comment comment) {
        Comment actualComment = commentRepo.findById(Id).get();
        actualComment.setCommentContent(comment.getCommentContent());
        actualComment.setInsertedDate(LocalDateTime.now());
        commentRepo.save(actualComment);
        return "Comment updated successfully";
    }
}
