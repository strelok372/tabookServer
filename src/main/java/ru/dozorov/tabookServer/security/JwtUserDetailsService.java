package ru.dozorov.tabookServer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dozorov.tabookServer.domain.User;
import ru.dozorov.tabookServer.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        GrantedAuthority authority = new GrantedAuthority() {
            long serialVersionUID = 1L;
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
//        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>(singl(authority));
        UserDetails d = new org.springframework.security.core.userdetails
                .User(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singleton(authority)
                );
        return d;
//        return new JwtUser(user);
    }
}
