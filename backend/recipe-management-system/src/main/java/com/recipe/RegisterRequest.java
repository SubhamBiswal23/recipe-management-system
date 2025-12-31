package com.recipe;

import lombok.Data;
import java.util.List;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String skillLevel;
    private List<String> dietPreferences;
    private List<String> allergies;
}