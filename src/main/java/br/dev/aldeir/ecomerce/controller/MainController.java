package br.dev.aldeir.ecomerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/") // Mapeia a requisição GET para a URL raiz
    public String mainPage() {
        return "index"; // Retorna o nome do template Thymeleaf (index.html)
    }
}