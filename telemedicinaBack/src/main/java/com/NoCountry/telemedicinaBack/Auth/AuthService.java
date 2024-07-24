package com.NoCountry.telemedicinaBack.Auth;

import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Jwt.JwtService;
import com.NoCountry.telemedicinaBack.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;

    private final AuthenticationManager authManager;


    public AuthResponse login(LoginRequest loginRequest) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        UserDetails user = userService.findByUsername(loginRequest.getUsername());
        String token=  jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();

    }

    //@Transactional
    public AuthResponse register(User userDto) {

        User user=userService.registrar(userDto);
        if(user!=null){
            userDto.setId(user.getId());
        }
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }

}
