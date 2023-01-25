package com.example.authjwt.configs.security;

import com.example.authjwt.entities.User;
import com.example.authjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existsUser = userRepository.findByEmail(username);

        if (existsUser == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return UserPrincipal.create(existsUser);
    }
}
