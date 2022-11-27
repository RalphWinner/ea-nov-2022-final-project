package com.ralph.repo;

import com.ralph.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepo extends JpaRepository<Media, Long> {
    List<Media> findAllByRating(int rating);
    List<Media> findAllByReleasedYear(int year);
    List<Media> findAllByGenre(String genre);
    List<Media> findAllByDirector(String director);
    List<Media> findAllByActor(String actor);
    List<Media> findAllByDuration(int duration);
}
