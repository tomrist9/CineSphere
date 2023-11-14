package com.example.movieapplication.connection;

import com.example.movieapplication.entities.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MovieService", url = "https://api.themoviedb.org/3")
public interface MovieFeignClient {

    @GetMapping("/movies/{movieId}")
    Movie getMovieInfo(@PathVariable Long movieId);

}
