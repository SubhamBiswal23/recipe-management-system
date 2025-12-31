package com.recipe;

import com.recipe.SpoonacularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {
    
    @Autowired
    private SpoonacularService spoonacularService;
    
    @GetMapping("/search")
    public String searchRecipes(@RequestParam String query, 
                                @RequestParam(defaultValue = "12") int number) {
        return spoonacularService.searchRecipes(query, number);
    }
    
    @GetMapping("/random")
    public String getRandomRecipes(@RequestParam(defaultValue = "12") int number) {
        return spoonacularService.getRandomRecipes(number);
    }
    
    @GetMapping("/{id}")
    public String getRecipeDetails(@PathVariable Long id) {
        return spoonacularService.getRecipeDetails(id);
    }
    
    @GetMapping("/by-ingredients")
    public String searchByIngredients(@RequestParam String ingredients,
                                     @RequestParam(defaultValue = "12") int number) {
        return spoonacularService.searchByIngredients(ingredients, number);
    }
}