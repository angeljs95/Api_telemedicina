package com.NoCountry.telemedicinaBack.Entity;

import com.NoCountry.telemedicinaBack.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private Long documento;
    private String pais;
    private String localidad;
    private String provincia;
    private Long telefono;

    @Column(nullable = false)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate fecha_Registro;
    private boolean estado;
    private String slug;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
