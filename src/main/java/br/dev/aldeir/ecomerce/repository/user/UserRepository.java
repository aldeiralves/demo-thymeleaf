package br.dev.aldeir.ecomerce.repository.user;

import br.dev.aldeir.ecomerce.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para encontrar um usuário pelo username. O Spring Data JPA implementa isso automaticamente.
    Optional<User> findByUsername(String username);
}