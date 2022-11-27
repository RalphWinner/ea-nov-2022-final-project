package com.ralph.service.Impl;

import com.ralph.config.RabbitMQConfig;
import com.ralph.dto.CommentDTO;
import com.ralph.dto.Converter;
import com.ralph.dto.MediaDTO;
import com.ralph.entity.Movie;
import com.ralph.entity.Serie;
import com.ralph.repo.MediaRepo;
import com.ralph.repo.MovieRepo;
import com.ralph.repo.SerieRepo;
import com.ralph.service.MediaService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RefreshScope
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private SerieRepo serieRepo;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private MediaRepo mediaRepo;
    @Autowired
    private Converter converter;
    @Value("${microservice.comment-service.endpoints.endpoint.uri}")
    private String COMMENT_URL;

    @Override
    public MediaDTO findMovieById(Long Id) {

        MediaDTO mediaDTO = converter.movietoMediaDTO(movieRepo.findById(Id).get());
        mediaDTO.setComments(getAllComments(mediaDTO.getId()));

        return mediaDTO;
    }

    @Override
    public MediaDTO findSerieById(Long Id) {
        MediaDTO mediaDTO = converter.serietoMediaDTO(serieRepo.findById(Id).get());
        mediaDTO.setComments(getAllComments(mediaDTO.getId()));

        return mediaDTO;
    }
    @Override
    public List<MediaDTO> findAllMovie() {
        List<MediaDTO> allMovie = movieRepo.findAll().stream()
                .map(movie -> converter.movietoMediaDTO(movie))
                .toList();
        for (MediaDTO mediaDTO: allMovie) {
            mediaDTO.setComments(getAllComments(mediaDTO.getId()));
        }
        return allMovie;
    }

    @Override
    public List<MediaDTO> findAllSerie() {
        List<MediaDTO> allSerie = serieRepo.findAll().stream()
                .map(serie -> converter.serietoMediaDTO(serie))
                .toList();
        for (MediaDTO mediaDTO: allSerie) {
            mediaDTO.setComments(getAllComments(mediaDTO.getId()));
        }
        return allSerie;
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepo.save(movie);
    }

    @Override
    public void updateMovie(Long Id, Movie movie) {
        Movie actualMovie = movieRepo.findById(Id).get();
        actualMovie.setActor(movie.getActor());
        actualMovie.setDirector(movie.getDirector());
        actualMovie.setDuration(movie.getDuration());
        actualMovie.setReleasedYear(movie.getReleasedYear());
        actualMovie.setGenre(movie.getGenre());
        movieRepo.save(actualMovie);
    }

    @Override
    public void deleteMovie(Long Id) {
        amqpTemplate.convertAndSend(RabbitMQConfig.mediaTopicExchange, RabbitMQConfig.mediaRoutingKey,Id);
        movieRepo.deleteById(Id);
    }
    @Override
    public void saveSerie(Serie serie) {
        serieRepo.save(serie);
    }

    @Override
    public void updateSerie(Long Id, Serie serie) {
        Serie actualSerie = serieRepo.findById(Id).get();
        actualSerie.setActor(serie.getActor());
        actualSerie.setDirector(serie.getDirector());
        actualSerie.setDuration(serie.getDuration());
        actualSerie.setReleasedYear(serie.getReleasedYear());
        actualSerie.setGenre(serie.getGenre());
        serieRepo.save(actualSerie);
    }

    @Override
    public void deleteSerie(Long Id) {
        serieRepo.deleteById(Id);
    }

    @Override
    public List<MediaDTO> findAllByReleasedYear(int year) {
        return mediaRepo.findAllByReleasedYear(year).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public List<MediaDTO> findAllByRating(int rating) {
        return mediaRepo.findAllByRating(rating).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public List<MediaDTO> findAllByGenre(String genre) {
        return mediaRepo.findAllByGenre(genre).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public List<MediaDTO> findAllByDirector(String director) {
        return mediaRepo.findAllByDirector(director).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public List<MediaDTO> findAllByActor(String actor) {
        return mediaRepo.findAllByActor(actor).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public List<MediaDTO> findAllByDuration(int duration) {
        return mediaRepo.findAllByDuration(duration).stream()
                .map(media -> converter.mediatoMediaDTO(media))
                .toList();
    }

    @Override
    public Boolean isMovieExist(Long Id) {
        return movieRepo.findById(Id).isPresent();
    }

    private List<CommentDTO> getAllComments(Long Id) {
        ResponseEntity<List> response
                = restTemplate.getForEntity(COMMENT_URL+Id, List.class);
        List<CommentDTO> comments = response.getBody();

        return comments;
    }
}
