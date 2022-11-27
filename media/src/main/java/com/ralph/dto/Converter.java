package com.ralph.dto;

import com.ralph.entity.Media;
import com.ralph.entity.Movie;
import com.ralph.entity.Serie;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public MediaDTO movietoMediaDTO(Movie movie){

        MediaDTO mediaDTO = MediaDTO.builder()
                        .director(movie.getDirector())
                        .actor(movie.getActor())
                        .duration(movie.getDuration())
                        .rating(movie.getRating())
                        .genre(movie.getGenre())
                        .releasedYear(movie.getReleasedYear())
                        .Id(movie.getId()).build();

        return mediaDTO;
    }

    public MediaDTO serietoMediaDTO(Serie serie){

        MediaDTO mediaDTO = MediaDTO.builder()
                .director(serie.getDirector())
                .actor(serie.getActor())
                .duration(serie.getDuration())
                .rating(serie.getRating())
                .genre(serie.getGenre())
                .releasedYear(serie.getReleasedYear())
                .Id(serie.getId()).build();

        return mediaDTO;
    }

    public MediaDTO mediatoMediaDTO(Media media){

        MediaDTO mediaDTO = MediaDTO.builder()
                .director(media.getDirector())
                .actor(media.getActor())
                .duration(media.getDuration())
                .rating(media.getRating())
                .genre(media.getGenre())
                .releasedYear(media.getReleasedYear())
                .Id(media.getId()).build();

        return mediaDTO;
    }
}
