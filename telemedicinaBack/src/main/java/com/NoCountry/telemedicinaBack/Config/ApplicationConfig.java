package com.NoCountry.telemedicinaBack.Config;
import com.NoCountry.telemedicinaBack.Repository.UserRepository;
import com.NoCountry.telemedicinaBack.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfig {

@Autowired
private UserRepository userRepository;


    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {

        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public UserDetailsService userDetailService(){
       return username ->
                userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(" User not Found"));
        /*InMemoryUserDetailsManager manager=  new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles()
                .build());
        return manager;
*/
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }






}
