package com.mischokacademy.jpademo.configuration;

import com.mischokacademy.jpademo.domain.User;
import com.mischokacademy.jpademo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                    .username("user")
                    .password(user.get().getPasswordEncoded())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
    }
}
