package br.dev.aldeir.ecomerce.service.user;

import br.dev.aldeir.ecomerce.model.user.User;
import br.dev.aldeir.ecomerce.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Importe esta classe

import java.util.Collections; // Importe esta classe para Collections.singletonList

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Constrói e retorna um objeto UserDetails que o Spring Security pode usar
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                // Aqui você definiria as roles/permissões do usuário.
                // Para este exemplo simples, vamos atribuir uma role "USER" padrão.
                // Se sua entidade User tiver um campo 'role', você usaria new SimpleGrantedAuthority(user.getRole())
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Exemplo de role padrão
        );
    }
}