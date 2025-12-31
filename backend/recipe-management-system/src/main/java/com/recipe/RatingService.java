package com.recipe;

import com.recipe.ApiResponse;
import com.recipe.Rating;
import com.recipe.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RatingService {
    
    @Autowired
    private RatingRepository ratingRepository;
    
    public ApiResponse addOrUpdateRating(Long userId, Long recipeId, Integer ratingValue, String review) {
        if (ratingValue < 1 || ratingValue > 5) {
            return new ApiResponse(false, "Rating must be between 1 and 5", null);
        }
        
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndRecipeId(userId, recipeId);
        
        Rating rating;
        String message;
        
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setRating(ratingValue);
            rating.setReview(review);
            message = "Rating updated";
        } else {
            rating = new Rating();
            rating.setUserId(userId);
            rating.setRecipeId(recipeId);
            rating.setRating(ratingValue);
            rating.setReview(review);
            message = "Rating added";
        }
        
        ratingRepository.save(rating);
        
        // Get updated stats
        Double avgRating = ratingRepository.getAverageRating(recipeId);
        Long totalRatings = ratingRepository.countByRecipeId(recipeId);
        
        Map<String, Object> data = new HashMap<>();
        data.put("averageRating", avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0);
        data.put("totalRatings", totalRatings);
        
        return new ApiResponse(true, message, data);
    }
    
    public ApiResponse getRecipeRatings(Long recipeId) {
        List<Rating> ratings = ratingRepository.findByRecipeId(recipeId);
        Double avgRating = ratingRepository.getAverageRating(recipeId);
        Long totalRatings = ratingRepository.countByRecipeId(recipeId);
        
        Map<String, Object> data = new HashMap<>();
        data.put("ratings", ratings);
        data.put("averageRating", avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0);
        data.put("totalRatings", totalRatings);
        
        return new ApiResponse(true, "Ratings fetched", data);
    }
}
