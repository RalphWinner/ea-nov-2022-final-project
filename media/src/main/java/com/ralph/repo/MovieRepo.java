package com.ralph.repo;

import com.ralph.entity.Media;
import com.ralph.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    Optional<Movie> findById(Long Id);
}
