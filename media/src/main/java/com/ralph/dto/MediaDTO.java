package com.ralph.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MediaDTO {
    private Long Id;
    private int releasedYear;
    private int rating;
    private String genre;
    private String director;
    private String actor;
    private int duration;
    private List<CommentDTO> comments;
}


