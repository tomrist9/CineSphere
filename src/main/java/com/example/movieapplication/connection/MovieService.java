package com.example.movieapplication.connection;

import com.example.movieapplication.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieFeignClient movieFeignClient;

    @Autowired
    public MovieService(MovieFeignClient movieFeignClient) {
        this.movieFeignClient = movieFeignClient;
    }

    public Movie getMovieInfo(Long movieId) {
        return movieFeignClient.getMovieInfo(movieId);
    }
}