package ru.dozorov.tabookServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dozorov.tabookServer.domain.User;
import ru.dozorov.tabookServer.dto.AuthRequestDto;
import ru.dozorov.tabookServer.security.JwtTokenProvider;
import ru.dozorov.tabookServer.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/auth/")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequestDto auth) {
        String username = auth.getUsername();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + username + " not found");
        }
        if (!userService.checkPassword(user, auth.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong login or password");
//            return "123123213";
        }
        String token = tokenProvider.createToken(username);
//        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("USER"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, auth.getPassword()));
        return ResponseEntity.ok(token);
//        return token;
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody AuthRequestDto auth) {
        String username = auth.getUsername();
        User user = userService.findByUsername(username);
        if (user != null){
//            return new ResponseDto(false, "This user already exist");
            return ResponseEntity.badRequest().body("This user already exist");
//            return new HashMap<String, Boolean>().put("result", false). ;
        }
        user = new User(auth.getUsername(), auth.getEmail(), auth.getPassword(), LocalDateTime.now());
        System.out.println(user);
        userService.register(user);
        String token = tokenProvider.createToken(username);
//        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("USER"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, auth.getPassword()));
//        return new ResponseDto(true, token);
        return ResponseEntity.ok(token);
    }


//        try {
//            String username = auth.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, auth.getPassword()));
//            User user = userService.findByUsername(username);
//            if (user == null){
//                throw new UsernameNotFoundException("User with name " + username + " not found");
//            }
//            String token = tokenProvider.createToken(username);
//            return ResponseEntity.ok(token);
//        } catch (AuthenticationException e){
//            throw new BadCredentialsException("Invalid username or password");
//        }
}
