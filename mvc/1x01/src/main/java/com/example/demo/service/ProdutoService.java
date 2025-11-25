package com.example.demo.service;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto adicionarProduto(Produto produto) {
        Produto novoProduto = new Produto(contadorId++, produto.getNome(), produto.getPreco());
        produtos.add(novoProduto);
        return novoProduto;

    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    p.setNome(produtoAtualizado.getNome());
                    p.setPreco(produtoAtualizado.getPreco());
                    return p;
                })
                .orElse(null);
    }

    public boolean deletarProduto(Long id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }
}
