package com.recipe;

import com.recipe.ApiResponse;
import com.recipe.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {
    
    @Autowired
    private RatingService ratingService;
    
    @PostMapping("/add")
    public ApiResponse addOrUpdateRating(@RequestParam Long userId,
                                        @RequestParam Long recipeId,
                                        @RequestParam Integer rating,
                                        @RequestParam(required = false) String review) {
        return ratingService.addOrUpdateRating(userId, recipeId, rating, review);
    }
    
    @GetMapping("/{recipeId}")
    public ApiResponse getRecipeRatings(@PathVariable Long recipeId) {
        return ratingService.getRecipeRatings(recipeId);
    }
}
