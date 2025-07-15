package br.dev.aldeir.ecomerce.controller.user;

import br.dev.aldeir.ecomerce.model.user.User;
import br.dev.aldeir.ecomerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users") // Mapeia o caminho base para /users
public class UserController {

    @Autowired
    private UserService userService;

    // Exibe a lista de todos os usuários
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-users"; // Retorna o nome do template Thymeleaf (list-users.html)
    }

    // Exibe o formulário para editar um usuário existente
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return userService.findById(id).map(user -> {
            // Importante: Não passe a senha criptografada para o formulário.
            // Crie um novo objeto User ou zere a senha antes de adicionar ao modelo.
            // Para simplificar, vamos criar um novo User com os dados, mas sem a senha.
            User userToEdit = new User();
            userToEdit.setId(user.getId());
            userToEdit.setUsername(user.getUsername());
            // userToEdit.setPassword(""); // Não preencha o campo de senha no formulário de edição

            model.addAttribute("user", userToEdit);
            return "form-user"; // Retorna o template do formulário
        }).orElseGet(() -> {
            ra.addFlashAttribute("mensagemErro", "Usuário não encontrado!");
            return "redirect:/users";
        });
    }

    // Salva as alterações de um usuário existente
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes ra) {
        try {
            userService.updateUser(user);
            ra.addFlashAttribute("mensagemSucesso", "Usuário atualizado com sucesso!");
            return "redirect:/users";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/users/edit/" + user.getId(); // Redireciona de volta para o formulário de edição com erro
        }
    }

    // Deleta um usuário
    @GetMapping("/delete/{id}") // Usamos GET aqui para simplificar, mas em produção um POST/DELETE seria mais seguro
    public String deleteUser(@PathVariable Long id, RedirectAttributes ra) {
        try {
            userService.deleteById(id);
            ra.addFlashAttribute("mensagemSucesso", "Usuário deletado com sucesso!");
        } catch (Exception e) { // Captura exceções caso o ID não exista, etc.
            ra.addFlashAttribute("mensagemErro", "Erro ao deletar usuário: " + e.getMessage());
        }
        return "redirect:/users";
    }
}