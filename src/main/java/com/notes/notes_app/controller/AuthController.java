package com.notes.notes_app.controller;

import com.notes.notes_app.repository.AppUserRepository;
import com.notes.notes_app.service.AppUserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
public class AuthController {
    private final AppUserService appUserService;

    public AuthController( AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam @NotBlank String username,@RequestParam @NotBlank String password, Model model)
    {
        try:

        return "register";
    }
    //TODO: Create repository layer. Than create service layer (AuthService). Only then fix AuthController.
}
