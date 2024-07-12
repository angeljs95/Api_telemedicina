package com.NoCountry.telemedicinaBack.Auth;

import com.NoCountry.telemedicinaBack.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value="login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping(value="register")
    public ResponseEntity<AuthResponse> register(@RequestBody User userDto){

        return ResponseEntity.ok(authService.register(userDto));
    }

}
