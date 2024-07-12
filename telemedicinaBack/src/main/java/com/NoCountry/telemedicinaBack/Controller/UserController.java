package com.NoCountry.telemedicinaBack.Controller;

import com.NoCountry.telemedicinaBack.Entity.User;
import com.NoCountry.telemedicinaBack.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registro")
    public ResponseEntity<User> registro(@RequestBody User usuario){

        if(usuario!= null){
            User user= userService.registrar(usuario);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver")
    public ResponseEntity<User> mostrarUsuario(@RequestParam String id){
        User usuario= userService.buscarUsuarioPorId(id);
        if(usuario!=null){
            return ResponseEntity.ok(usuario);
        } else {
        return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/eliminar")
    public ResponseEntity<Boolean> eliminar (String id){
        User usuario= userService.buscarUsuarioPorId(id);
        if(usuario!= null){
            userService.eliminarUsuario(id);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

}
