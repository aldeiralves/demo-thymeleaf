package br.dev.aldeir.ecomerce.repository;

import br.dev.aldeir.ecomerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um componente de acesso a dados
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // JpaRepository já fornece métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
    // Você pode adicionar métodos personalizados aqui se precisar de consultas mais específicas
}