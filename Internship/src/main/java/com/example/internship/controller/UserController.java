package com.example.internship.controller;

import com.example.internship.dto.AuthRequest;
import com.example.internship.dto.StringDTO;
import com.example.internship.dto.UpdateUserDTO;
import com.example.internship.entity.UserInfo;
import com.example.internship.service.JwtService;
import com.example.internship.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void addUser(@RequestBody UserInfo userInfo) {
        userService.addUser(userInfo);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<StringDTO> authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }

    @GetMapping("/getUserInfo/{username}")
    public UserInfo getUserInfo(@PathVariable String username) {
        return userService.getUser(username).get();
    }

    @GetMapping("/getUserInfoByToken")
    public UserInfo getUserInfoByToken(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.getUserByToken(authorizationHeader);
    }

    @PutMapping("/update-urls")
    public UserInfo updateUrls(@RequestBody UpdateUserDTO updateUser, @RequestHeader("Authorization") String authorizationHeader) {
        return userService.updateUrls(authorizationHeader, updateUser);
    }
}
