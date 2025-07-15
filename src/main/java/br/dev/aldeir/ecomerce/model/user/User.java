package br.dev.aldeir.ecomerce.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users") // Nome da tabela no banco de dados
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Garante que o username seja único e não nulo
    private String username;

    @Column(nullable = false)
    private String password;

    // Você pode adicionar um campo de role se quiser ter diferentes tipos de usuários (e.g., ADMIN, USER)
    // private String role;

    // Construtor para facilitar a criação de novos usuários sem o ID
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}