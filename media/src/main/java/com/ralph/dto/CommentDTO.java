package com.ralph.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long Id;
    private Long mediaId;
    private String commentContent;
    private LocalDateTime insertedDate;
    private Long userId;
}
