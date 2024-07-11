package com.NoCountry.telemedicinaBack.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Inicio {

    @GetMapping("")
    public ResponseEntity<String> inicio(){
        String saludar= "hola amigos! Bienvenidos";
        return ResponseEntity.ok(saludar);
    }
}
