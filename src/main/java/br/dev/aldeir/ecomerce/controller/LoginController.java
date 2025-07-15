package br.dev.aldeir.ecomerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login") // Mapeia requisições GET para /login
    public String login() {
        return "login"; // Retorna o template Thymeleaf (login.html)
    }

    // O Spring Security lida com a lógica de post de login e logout
}