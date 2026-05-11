package com.penguin.cake.auth;

import com.penguin.cake.common.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", "admin");
        return ApiResponse.ok("登录成功", Map.of(
            "token", "mock-jwt-token-for-" + username,
            "username", username,
            "role", username.contains("store") ? "STORE_MANAGER" : "HQ_ADMIN",
            "home", "/admin/map"
        ));
    }
}
