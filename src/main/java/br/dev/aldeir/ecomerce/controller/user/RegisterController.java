package br.dev.aldeir.ecomerce.controller.user;

import br.dev.aldeir.ecomerce.model.user.User;
import br.dev.aldeir.ecomerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // Exibe o formulário de registro de usuário
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Adiciona um objeto User vazio ao modelo
        return "register"; // Retorna o template Thymeleaf (register.html)
    }

    // Processa o envio do formulário de registro
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerNewUser(user);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso! Faça login.");
            return "redirect:/login"; // Redireciona para a página de login
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/register"; // Redireciona de volta para o formulário de registro com erro
        }
    }
}