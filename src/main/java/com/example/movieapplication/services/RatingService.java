package com.example.movieapplication.services;

import com.example.movieapplication.entities.Rating;
import org.springframework.stereotype.Service;
import com.example.movieapplication.repositories.RatingRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RatingService
{
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }
    public Rating getRatingById(Long ratingId){
        return ratingRepository.findById(ratingId)
                .orElseThrow(()-> new EntityNotFoundException("Rating not found with"+ ratingId));
    }
    public Rating createRating(Rating newRating){
        return ratingRepository.save(newRating);
    }
    public Rating updateRating(Long ratingId, Rating updatedRating){
        Rating existingRating =getRatingById(ratingId);
        existingRating.setValue(updatedRating.getValue());

        return ratingRepository.save(updatedRating);
    }
    public void deleteRating(Long ratingId){
        Rating existingRating=getRatingById(ratingId);
        ratingRepository.delete(existingRating);
    }
}
