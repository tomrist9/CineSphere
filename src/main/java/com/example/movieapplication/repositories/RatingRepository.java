package com.example.movieapplication.repositories;

import com.example.movieapplication.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findMovieById(Long movieId);
}
