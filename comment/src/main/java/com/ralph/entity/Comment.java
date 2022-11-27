package com.ralph.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.LongFunction;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long mediaId;
    private String commentContent;
    private LocalDateTime insertedDate;
    private Long userId;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDelete;
    private LocalDateTime deleteDateTime;
}
