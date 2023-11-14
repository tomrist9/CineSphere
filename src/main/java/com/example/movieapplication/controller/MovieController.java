package com.example.movieapplication.controller;

import com.example.movieapplication.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.example.movieapplication.services.MovieService;

import java.util.List;
//
//@RestController
//@RequestMapping("/movies")
//public class MovieController {
//    private final MovieService movieService;
//
//    @Autowired
//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }
//        @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies(){
//        List<Movie> movies=movieService.getAllMovies();
//        return ResponseEntity.ok(movies);
//    }
//    @GetMapping("/{movieId}")
//    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
//        Movie movie = movieService.getMovieById(movieId);
//        return ResponseEntity.ok(movie);
//
//    }
//}
