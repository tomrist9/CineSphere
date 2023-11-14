package com.example.movieapplication.controller;

import com.example.movieapplication.entities.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.movieapplication.repositories.RatingRepository;
import com.example.movieapplication.services.RatingService;

import java.util.List;


@RestController

@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingRepository ratingRepository, RatingService ratingService) {
        this.ratingService = ratingService;

    }
    @GetMapping
    public List<Rating> getAllRatings(){

        return ratingService.getAllRatings();
    }
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId) {
        Rating rating = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(rating);

    }
//   public ResponseEntity<Rating> createRating(@RequestBody Rating newRating){
//
//   }
   @PutMapping("/{ratingId}")
   public ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, @RequestBody Rating updatedRating){
        Rating modifiedRating=ratingService.updateRating(ratingId, updatedRating);
        return  ResponseEntity.ok(modifiedRating);
   }

}
