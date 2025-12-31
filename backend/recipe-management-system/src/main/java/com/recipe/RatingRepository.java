package com.recipe;

import com.recipe.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRecipeId(Long recipeId);
    Optional<Rating> findByUserIdAndRecipeId(Long userId, Long recipeId);
    
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.recipeId = :recipeId")
    Double getAverageRating(Long recipeId);
    
    Long countByRecipeId(Long recipeId);
}