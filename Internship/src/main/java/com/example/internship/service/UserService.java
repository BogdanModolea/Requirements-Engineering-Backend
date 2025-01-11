package com.example.internship.service;

import com.example.internship.dto.AuthRequest;
import com.example.internship.entity.UserInfo;
import com.example.internship.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * the user service implementation
 */
@Service
public class UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Environment env;

    /**
     * register a new user
     *
     * @param userInfo
     * @return
     */
    public void addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setEnabled(true);
        userInfoRepository.save(userInfo);
    }

    public String authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        System.out.println("Authorities: " + authentication.getAuthorities());
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        return "Invalid";
    }

    /**
     * get a user by username
     *
     * @param username
     * @return
     */
    public Optional<UserInfo> getUser(String username) {
        return userInfoRepository.findByName(username);
    }

    public String getUserRole(String username) {
        return userInfoRepository.getRoleByUsername(username);
    }
}
