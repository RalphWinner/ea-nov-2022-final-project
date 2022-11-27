package com.ralph.dto;

import com.ralph.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public CommentDTO commentToDTO(Comment comment){
        CommentDTO commentDTO = CommentDTO.builder()
                .Id(comment.getId())
                .insertedDate(comment.getInsertedDate())
                .mediaId(comment.getMediaId())
                .userId(comment.getUserId())
                .commentContent(comment.getCommentContent())
                .build();

        return commentDTO;

    }

}
