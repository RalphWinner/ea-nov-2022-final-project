package com.ralph.controller;

import com.ralph.MediaApplication;
import com.ralph.dto.MediaDTO;
import com.ralph.entity.Movie;
import com.ralph.entity.Serie;
import com.ralph.service.MediaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    private static final Logger logger =
            LoggerFactory.getLogger(MediaApplication.class);
    @Autowired
    private MediaService mediaService;

    @ApiOperation(value = "Get All Movies", notes = "to get all the movies from the system")
    @GetMapping("/movie")
    List<MediaDTO> findAllMovie(){
        logger.info( "Get All Movies");
        logger.error("Get All Movies Error");
        return mediaService.findAllMovie();
    }

    @ApiOperation(value = "Get All Series", notes = "to get all the series from the system")
    @GetMapping("/serie")
    List<MediaDTO> findAllSerie(){
        return mediaService.findAllSerie();
    }

    @ApiOperation(value = "Save a movie")
    @PostMapping("/movie")
    void saveMovie(@RequestBody Movie movie){
        mediaService.saveMovie(movie);
    }

    @ApiOperation(value = "Save a serie")
    @PostMapping("/serie")
    void saveSerie(@RequestBody Serie serie){
        mediaService.saveSerie(serie);
    }

    @ApiOperation(value = "Delete a movie", notes = "will also delete all comments for that movie")
    @DeleteMapping("/movie/{Id}")
    void deleteMovie(@PathVariable Long Id){
        mediaService.deleteMovie(Id);
    }

    @ApiOperation(value = "Delete a serie", notes = "will also delete all comments for that serie")
    @DeleteMapping("/serie/{Id}")
    void deleteSerie(@PathVariable Long Id){
        mediaService.deleteSerie(Id);
    }

    @PutMapping("/movie/{Id}")
    void updateMovie(@PathVariable Long Id, @RequestBody Movie movie){
        mediaService.updateMovie(Id, movie);
    }

    @PutMapping("/serie/{Id}")
    void update(@PathVariable Long Id, @RequestBody Serie serie){
        mediaService.updateSerie(Id, serie);
    }

    @GetMapping("/movie/{id}")
    boolean isMovieExist(@PathVariable Long id){
        return mediaService.isMovieExist(id);
    }

}
