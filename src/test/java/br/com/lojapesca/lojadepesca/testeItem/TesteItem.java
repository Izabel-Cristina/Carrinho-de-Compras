package br.com.lojapesca.lojadepesca.testeItem;

import br.com.lojapesca.lojadepesca.bo.ItemBO;
import br.com.lojapesca.lojadepesca.bo.ProdutoBo;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.repository.ItemRepository;
import br.com.lojapesca.lojadepesca.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class TesteItem {
    @Autowired
    ProdutoRepository produtoRepository;
    private ItemBO itemBO = new ItemBO(null);


    @Before
    public void setUp() {
        itemBO = new ItemBO(null);
    }

    @Test
    public void testAtualizarQuantiadeItem() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO(1L, "ssssssssss","ddddddd", 130.00);

        ItemDTO itemDTO = new ItemDTO(124L, produtoDTO, 16, 130.00);
        ItemDTO resultado = itemBO.atualizarQuantidadeItem(itemDTO);
        assert(resultado.getQuantidade().equals(16));
    }


}

