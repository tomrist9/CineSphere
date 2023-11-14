package com.example.movieapplication.repositories;

import com.example.movieapplication.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    List<Category> findMovieById(Long movieId);
}
