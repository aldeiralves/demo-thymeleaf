package br.dev.aldeir.ecomerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela no DB
@Table(name = "produtos") // Nome da tabela no banco de dados
@Data // Anotação do Lombok para gerar getters, setters, toString, equals e hashCode
@NoArgsConstructor // Anotação do Lombok para gerar um construtor sem argumentos
@AllArgsConstructor // Anotação do Lombok para gerar um construtor com todos os argumentos
public class Produto {

    @Id // Marca o campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automática de ID (auto-incremento)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    // Construtor sem ID para criação de novos produtos
    public Produto(String nome, String descricao, double preco, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}