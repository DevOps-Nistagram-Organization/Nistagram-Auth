package com.nistagram.authentication.config;

import com.nistagram.authentication.model.User;
import com.nistagram.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Method gets the User with given username from database
    // if user with that username doesn't  exists the method will throw exception
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User agent = userRepository.findByUsername(username);
        if (agent == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return agent;
        }
    }

}

