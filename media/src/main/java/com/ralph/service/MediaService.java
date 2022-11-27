package com.ralph.service;

import com.ralph.dto.MediaDTO;
import com.ralph.entity.Movie;
import com.ralph.entity.Serie;

import java.util.List;

public interface MediaService {
    MediaDTO findMovieById(Long Id);
    MediaDTO findSerieById(Long Id);
    List<MediaDTO> findAllMovie();
    void saveMovie(Movie movie);
    void updateMovie(Long Id, Movie movie);
    void deleteMovie(Long Id);

    List<MediaDTO> findAllSerie();
    void saveSerie(Serie serie);
    void updateSerie(Long Id, Serie serie);
    void deleteSerie(Long Id);

    List<MediaDTO> findAllByReleasedYear(int year);
    List<MediaDTO> findAllByRating(int rating);
    List<MediaDTO> findAllByGenre(String genre);
    List<MediaDTO> findAllByDirector(String director);
    List<MediaDTO> findAllByActor(String actor);
    List<MediaDTO> findAllByDuration(int duration);
    Boolean isMovieExist(Long Id);
}
