package com.nistagram.authentication.service;

import com.nistagram.authentication.client.UserClient;
import com.nistagram.authentication.config.JwtTokenUtil;
import com.nistagram.authentication.model.Authority;
import com.nistagram.authentication.model.MyUser;
import com.nistagram.authentication.model.dto.LoginRequestDTO;
import com.nistagram.authentication.model.dto.RegistrationRequestDTO;
import com.nistagram.authentication.model.dto.UserInfoDTO;
import com.nistagram.authentication.model.dto.UserInfoRegistrationDTO;
import com.nistagram.authentication.repository.AuthorityRepository;
import com.nistagram.authentication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthorityRepository authorityRepository;
    private final UserClient userClient;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenUtil jwtTokenUtil, AuthorityRepository authorityRepository, UserClient userClient) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authorityRepository = authorityRepository;
        this.userClient = userClient;
    }


    public String login(LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    dto.getUsername(), dto.getPassword()));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new BadCredentialsException(dto.getUsername());
        }
        final MyUser user = userRepository.findByUsername(dto.getUsername());

        return jwtTokenUtil.generateToken(user);

    }

    public MyUser register(RegistrationRequestDTO dto) throws Exception {
        Authority authority = null;
        Set<Authority> authorityList = new HashSet<>();
        if (dto.getAgent()) {
            authorityList.add(authorityRepository.findByName("AGENT"));
        } else {
            authorityList.add(authorityRepository.findByName("USER"));
        }
        MyUser user = new MyUser(null, dto.getUsername(), (new BCryptPasswordEncoder().encode(dto.getPassword())), authorityList);
        user = userRepository.save(user);
        String token = jwtTokenUtil.generateToken(user);
        UserInfoRegistrationDTO userInfoRegistrationDTO = new UserInfoRegistrationDTO(dto.getUsername(),
                dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getGender(), dto.getDateOfBirth(), dto.getAgent());
        ResponseEntity<UserInfoDTO> response = userClient.saveUser(userInfoRegistrationDTO, "Bearer " + token);
        if (response.getStatusCode() == HttpStatus.OK) {
            return userRepository.save(user);
        }
        throw new Exception("Error creating account");
    }

    public String refreshToken(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            MyUser user = userRepository.findByUsername(username);
            return jwtTokenUtil.generateToken(user);
        }
        return null;
    }
}
