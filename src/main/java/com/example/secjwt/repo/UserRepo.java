package com.example.secjwt.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepo {

    private  final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("u1","u1", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("u2","u2", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
    );

    public UserDetails findUserByEmail(String username)  {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
