package com.example.internship.service;

import com.example.internship.dto.AuthRequest;
import com.example.internship.dto.StringDTO;
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

    public StringDTO authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return new StringDTO(jwtService.generateToken(authRequest.getUsername()));
        }
        return new StringDTO("Invalid");
    }

    public Optional<UserInfo> getUser(String username) {
        return userInfoRepository.findByName(username);
    }

    public UserInfo getUserByToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user;
    }

    public String getUserRole(String username) {
        return userInfoRepository.getRoleByUsername(username);
    }

    public UserInfo updateUrls(String authorizationHeader, String resumeUrl, String githubUrl) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        UserInfo user = userInfoRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (resumeUrl != null && !resumeUrl.isEmpty()) {
            user.setResumeUrl(resumeUrl);
        }
        if (githubUrl != null && !githubUrl.isEmpty()) {
            user.setGithubUrl(githubUrl);
        }

        return userInfoRepository.save(user);
    }
}
