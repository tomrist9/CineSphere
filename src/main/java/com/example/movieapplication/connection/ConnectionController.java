package com.example.movieapplication.connection;


import com.example.movieapplication.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class ConnectionController {
    private final MovieFeignClient movieFeignClient;

    @GetMapping("{id}")
    public Movie getMovieInfo(@PathVariable Long id) {
        String apiKey = "4e61a7707a91512e8a948d778aec0225";
        return movieFeignClient.getMovieInfo(id);
    }
}
