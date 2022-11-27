package com.ralph.entity;

import lombok.Data;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "media_type")
@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int releasedYear;
    private int rating;
    private String genre;
    private String director;
    private String actor;
    private int duration;
}
