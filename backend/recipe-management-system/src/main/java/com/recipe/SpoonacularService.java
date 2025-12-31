package com.recipe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpoonacularService {
    
    @Value("${spoonacular.api.key}")
    private String apiKey;
    
    @Value("${spoonacular.api.base-url}")
    private String baseUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // Search recipes
    public String searchRecipes(String query, int number) {
        String url = String.format("%s/recipes/complexSearch?query=%s&number=%d&addRecipeInformation=true&apiKey=%s",
                baseUrl, query, number, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
    
    // Get random recipes
    public String getRandomRecipes(int number) {
        String url = String.format("%s/recipes/random?number=%d&apiKey=%s",
                baseUrl, number, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
    
    // Get recipe details
    public String getRecipeDetails(Long recipeId) {
        String url = String.format("%s/recipes/%d/information?apiKey=%s",
                baseUrl, recipeId, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
    
    // Search by ingredients
    public String searchByIngredients(String ingredients, int number) {
        String url = String.format("%s/recipes/findByIngredients?ingredients=%s&number=%d&apiKey=%s",
                baseUrl, ingredients, number, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}