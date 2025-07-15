package br.dev.aldeir.ecomerce.controller;

import br.dev.aldeir.ecomerce.model.Produto;
import br.dev.aldeir.ecomerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // Indica que esta classe é um controlador MVC
@RequestMapping("/produtos") // Define o caminho base para as URLs de produtos
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Exibe a lista de todos os produtos
    @GetMapping // Mapeia requisições GET para /produtos
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos); // Adiciona a lista de produtos ao modelo para Thymeleaf
        return "lista-produtos"; // Retorna o nome do template Thymeleaf (lista-produtos.html)
    }

    // Exibe o formulário para adicionar um novo produto
    @GetMapping("/novo")
    public String exibirFormularioNovoProduto(Model model) {
        model.addAttribute("produto", new Produto()); // Adiciona um objeto Produto vazio ao modelo
        return "form-produto"; // Retorna o template do formulário
    }

    // Salva um novo produto ou atualiza um existente
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, RedirectAttributes ra) {
        produtoService.save(produto);
        ra.addFlashAttribute("mensagemSucesso", "Produto salvo com sucesso!");
        return "redirect:/produtos"; // Redireciona para a lista de produtos
    }

    // Exibe o formulário para editar um produto existente
    @GetMapping("/editar/{id}")
    public String exibirFormularioEditarProduto(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return produtoService.findById(id).map(produto -> {
            model.addAttribute("produto", produto);
            return "form-produto"; // Retorna o template do formulário preenchido
        }).orElseGet(() -> {
            ra.addFlashAttribute("mensagemErro", "Produto não encontrado!");
            return "redirect:/produtos"; // Redireciona se o produto não for encontrado
        });
    }

    // Deleta um produto
    @GetMapping("/deletar/{id}") // Usamos GET aqui para simplificar, mas em produção um POST/DELETE seria mais seguro
    public String deletarProduto(@PathVariable Long id, RedirectAttributes ra) {
        produtoService.deleteById(id);
        ra.addFlashAttribute("mensagemSucesso", "Produto deletado com sucesso!");
        return "redirect:/produtos"; // Redireciona para a lista de produtos
    }
}