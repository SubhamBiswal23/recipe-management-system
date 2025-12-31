package com.recipe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;
    
    @PostMapping("/add")
    public ApiResponse addFavorite(@RequestParam Long userId,
                                   @RequestParam Long recipeId,
                                   @RequestParam String recipeTitle,
                                   @RequestParam String recipeImage) {
        return favoriteService.addFavorite(userId, recipeId, recipeTitle, recipeImage);
    }
    
    @DeleteMapping("/remove")
    public ApiResponse removeFavorite(@RequestParam Long userId,
                                     @RequestParam Long recipeId) {
        return favoriteService.removeFavorite(userId, recipeId);
    }
    
    @GetMapping("/{userId}")
    public ApiResponse getUserFavorites(@PathVariable Long userId) {
        return favoriteService.getUserFavorites(userId);
    }
    
    @GetMapping("/check")
    public ApiResponse isFavorite(@RequestParam Long userId,
                                 @RequestParam Long recipeId) {
        return favoriteService.isFavorite(userId, recipeId);
    }
}
