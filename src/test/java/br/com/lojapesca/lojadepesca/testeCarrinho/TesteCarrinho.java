package br.com.lojapesca.lojadepesca.testeCarrinho;

import br.com.lojapesca.lojadepesca.bo.CarrinhoBo;
import br.com.lojapesca.lojadepesca.bo.ItemBO;
import br.com.lojapesca.lojadepesca.domain.Carrinho;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.mapper.ProdutoMapper;
import br.com.lojapesca.lojadepesca.repository.CarrinhoRepository;
import br.com.lojapesca.lojadepesca.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;


public class TesteCarrinho {
    private ProdutoRepository produtoRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    private ProdutoMapper produtoMapper;
    private CarrinhoBo carrinhoBo = new CarrinhoBo(produtoMapper, carrinhoRepository, null, produtoRepository, null);

    ItemBO itemBO = new ItemBO(produtoRepository);


    @Before
    public void setUp() {
        carrinhoBo = new CarrinhoBo(null, carrinhoRepository, null, produtoRepository, null);
    }


    @Test
    public void testAdicionarProdutoCarrinho() throws Exception {

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setId(1L);

        ProdutoDTO produtoDTO = new ProdutoDTO(7L, "aa", "aa", 10.0);
        ItemDTO itemDTO = new ItemDTO(1L, produtoDTO, 2, 20.0);

        itemBO.adicionarItens(itemDTO);
        Double precoItem = 0.0;

        // Corrigir a configuração dos itens para uma lista
        carrinhoDTO.setItens(Collections.singletonList(itemDTO));
        carrinhoDTO.setPrecoTotalProduto(precoItem += itemDTO.getValorTotal());

        Integer quantidadeProduto = 0;
        carrinhoDTO.setQuantidadeProduto(quantidadeProduto += itemDTO.getQuantidade());


        // Chamar o método que você deseja testar
        assertThrows(Exception.class, () -> carrinhoBo.adicionarProdutoCarrinho(carrinhoDTO));
        assert (carrinhoDTO.getQuantidadeProduto().equals(2));
        assertNotEquals(20, carrinhoDTO.getPrecoTotalProduto());
    }
}

