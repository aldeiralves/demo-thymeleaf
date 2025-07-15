package br.dev.aldeir.ecomerce.service.user;

import br.dev.aldeir.ecomerce.model.user.User;
import br.dev.aldeir.ecomerce.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username já existe: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // --- Métodos para Manutenção de Usuários ---

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        // Busca o usuário existente para garantir que não estamos sobrescrevendo a senha com texto puro
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername()); // Permite atualizar o username

            // Somente atualiza a senha se uma nova senha for fornecida (e não estiver vazia)
            // Isso evita criptografar uma string vazia ou nula se o campo de senha no formulário estiver vazio
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            // Caso contrário, a senha antiga criptografada permanece

            return userRepository.save(userToUpdate);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + user.getId());
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}