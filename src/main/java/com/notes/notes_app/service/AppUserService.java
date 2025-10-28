//package com.notes.notes_app.service;
//
//import com.notes.notes_app.model.AppUser;
//import com.notes.notes_app.repository.AppUserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AppUserService implements UserDetailsService
//{
//    private AppUserRepository appUserRepository;
//
//    public AppUserService (AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//    {
//        AppUser u = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return org.springframework.security.core.userdetails.User
//                .withUsername(u.getUsername())
//                .password(u.getPassword())
//                .authorities(new SimpleGrantedAuthority(u.getRol()))
//                .build();
//    }
//}
