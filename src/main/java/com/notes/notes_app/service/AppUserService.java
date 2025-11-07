package com.notes.notes_app.service;

import com.notes.notes_app.controller.DuplicateUsernameException;
import com.notes.notes_app.model.AppUser;
import com.notes.notes_app.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService
{
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;

    public AppUserService (AppUserRepository appUserRepository,  PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        AppUser u = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(new SimpleGrantedAuthority(u.getRol()))
                .build();
    }

    public void register(String username, String password)
    {
        if(appUserRepository.existsByUsername(username))
            throw new DuplicateUsernameException("Username already taken");
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(encoder.encode(password));
        u.setRol("ROLE_USER");
        appUserRepository.save(u);
    }
}
