package com.ralph.repo;

import com.ralph.entity.Media;
import com.ralph.entity.Movie;
import com.ralph.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepo extends JpaRepository<Serie, Long> {
    List<Serie> findAll();
    Optional<Serie> findById(Long Id);
}
