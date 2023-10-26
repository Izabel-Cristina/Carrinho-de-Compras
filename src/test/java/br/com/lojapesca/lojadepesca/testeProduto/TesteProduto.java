package br.com.lojapesca.lojadepesca.testeProduto;

import br.com.lojapesca.lojadepesca.domain.Produto;
import org.junit.jupiter.api.Test;
import br.com.lojapesca.lojadepesca.bo.ProdutoBo;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import org.junit.Before;
import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TesteProduto {
    private ProdutoBo produtoBo = new ProdutoBo();


    @Before
    public void setUp() {
        produtoBo = new ProdutoBo();
    }


    @Test
    public void testInserirProdutoSucesso() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Vara");
        produtoDTO.setDescricao("Vara intermediária para auxiliar na sua pesca");
        produtoDTO.setPreco(170.00);
        String mensagem = produtoBo.inserirProduto(produtoDTO);
        assertEquals("Produto incluído com sucesso.", mensagem);
    }

    @Test
    public void testInserirProdutoNomeVazio() {
        ProdutoDTO produtoDTO = new ProdutoDTO("", "Descrição do Produto", 10.0);
        assertThrows(Exception.class, () -> produtoBo.inserirProduto(produtoDTO));
    }

    @Test
    public void testInserirProdutoDescricaoVazia() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Nome do Produto", "", 10.0);
        assertThrows(Exception.class, () -> produtoBo.inserirProduto(produtoDTO));
    }

    @Test
    public void testInserirProdutoPrecoZero() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Nome do Produto", "Descrição do Produto", 0.0);
        assertThrows(Exception.class, () -> produtoBo.inserirProduto(produtoDTO));
    }

    @Test
    public void testListarProdutos() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        //Verificar se a lista está vazia
        List<Produto> produtos = produtoBo.listarProdutos();
// Verifique se a lista de produtos não é nula e não está vazia
        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
    }

    @Test
    public void testNomeNull()  {
        String nome = new String("");

        List<Produto> produtos = produtoBo.obterProdutosNome(nome);
        assertNotNull(nome);
        assertFalse(produtos.isEmpty());
    }



    @Test
    public void testFiltrarNome() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Vara", "", 0.0);
        List<Produto> produtos = produtoBo.obterProdutosNome(produtoDTO.getNome());
        //Verificar lista vazia
        assertNotNull(produtos);
    }

}

