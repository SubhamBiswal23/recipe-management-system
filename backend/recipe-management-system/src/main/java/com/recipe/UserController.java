package com.recipe;

import com.recipe.ApiResponse;
import com.recipe.LoginRequest;
import com.recipe.RegisterRequest;
import com.recipe.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
    
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    
    @GetMapping("/profile/{userId}")
    public ApiResponse getProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }
}