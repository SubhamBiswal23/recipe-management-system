package com.recipe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavoriteService {
    
    @Autowired
    private FavoriteRepository favoriteRepository;
    
    public ApiResponse addFavorite(Long userId, Long recipeId, String recipeTitle, String recipeImage) {
        // Check if already favorited
        if (favoriteRepository.existsByUserIdAndRecipeId(userId, recipeId)) {
            return new ApiResponse(false, "Already in favorites", null);
        }
        
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setRecipeId(recipeId);
        favorite.setRecipeTitle(recipeTitle);
        favorite.setRecipeImage(recipeImage);
        
        favoriteRepository.save(favorite);
        
        return new ApiResponse(true, "Added to favorites", null);
    }
    
    @Transactional
    public ApiResponse removeFavorite(Long userId, Long recipeId) {
        favoriteRepository.deleteByUserIdAndRecipeId(userId, recipeId);
        return new ApiResponse(true, "Removed from favorites", null);
    }
    
    public ApiResponse getUserFavorites(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return new ApiResponse(true, "Favorites fetched", favorites);
    }
    
    public ApiResponse isFavorite(Long userId, Long recipeId) {
        boolean exists = favoriteRepository.existsByUserIdAndRecipeId(userId, recipeId);
        return new ApiResponse(true, "Checked", exists);
    }
}
