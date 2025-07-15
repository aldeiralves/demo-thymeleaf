package br.dev.aldeir.ecomerce.service;

import br.dev.aldeir.ecomerce.model.Produto;
import br.dev.aldeir.ecomerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um componente de serviço (lógica de negócio)
public class ProdutoService {

    @Autowired // Injeta uma instância de ProdutoRepository
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}