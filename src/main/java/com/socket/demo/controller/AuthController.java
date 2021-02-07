package com.socket.demo.controller;


import com.socket.demo.model.JwtResponse;
import com.socket.demo.model.Role;
import com.socket.demo.model.User;
import com.socket.demo.service.IUserService;
import com.socket.demo.service.JwtService;
import com.socket.demo.service.iRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private iRoleService roleService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        Set<Role> roles = new HashSet<>();
        user.getRoles().forEach(role -> {
            if (role.getName().equals("admin")) {
                Role adminRole = roleService.findByName("ROLE_ADMIN").get();
                roles.add(adminRole);
            } else {
                Role userRole = roleService.findByName("ROLE_USER").get();
                roles.add(userRole);
            }
        });
        newUser.setRoles(roles);
        userService.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }
}
