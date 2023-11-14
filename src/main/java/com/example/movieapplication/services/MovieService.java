package com.example.movieapplication.services;

import com.example.movieapplication.entities.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.movieapplication.repositories.MovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
//@Component
//@Service
//public class MovieService {
//
//
//
//    private MovieRepository movieRepository;
//
//
//
//    @Autowired
//
//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//    public List<Movie> getAllMovies(){
//      return movieRepository.findAll();
//    }
//    public Movie getMovieById(Long movieId) {
//        return movieRepository.findById(movieId)
//                .orElseThrow(()-> new EntityNotFoundException("Movie not found with id"+ movieId));
//
//    }
//}
