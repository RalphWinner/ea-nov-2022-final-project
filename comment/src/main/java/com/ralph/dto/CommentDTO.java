package com.ralph.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDTO {
    private Long Id;
    private Long mediaId;
    private String commentContent;
    private LocalDateTime insertedDate;
    private Long userId;
}
