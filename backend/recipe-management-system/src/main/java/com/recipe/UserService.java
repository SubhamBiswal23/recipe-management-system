package com.recipe;

import com.recipe.ApiResponse;
import com.recipe.LoginRequest;
import com.recipe.RegisterRequest;
import com.recipe.User;
import com.recipe.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public ApiResponse register(RegisterRequest request) {
        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false, "Email already exists", null);
        }
        
        // Check if username exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse(false, "Username already exists", null);
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In production, hash this!
        user.setSkillLevel(request.getSkillLevel());
        
        if (request.getDietPreferences() != null) {
            user.setDietPreferences(request.getDietPreferences());
        }
        
        if (request.getAllergies() != null) {
            user.setAllergies(request.getAllergies());
        }
        
        userRepository.save(user);
        
        return new ApiResponse(true, "Registration successful", null);
    }
    
    public ApiResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        
        if (userOpt.isEmpty()) {
            return new ApiResponse(false, "Invalid email or password", null);
        }
        
        User user = userOpt.get();
        
        if (!user.getPassword().equals(request.getPassword())) {
            return new ApiResponse(false, "Invalid email or password", null);
        }
        
        // Create user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("skillLevel", user.getSkillLevel());
        
        return new ApiResponse(true, "Login successful", userData);
    }
    
    public ApiResponse getUserProfile(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (userOpt.isEmpty()) {
            return new ApiResponse(false, "User not found", null);
        }
        
        User user = userOpt.get();
        
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("skillLevel", user.getSkillLevel());
        userData.put("dietPreferences", user.getDietPreferences());
        userData.put("allergies", user.getAllergies());
        userData.put("createdAt", user.getCreatedAt());
        
        return new ApiResponse(true, "Profile fetched", userData);
    }
}