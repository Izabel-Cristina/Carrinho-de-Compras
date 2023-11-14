package br.com.lojapesca.lojadepesca.service;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;

import java.util.List;

public interface ProdutoService {
    ResponseDTO<ProdutoDTO> inserirProduto(ProdutoDTO produtoDTO);
    ResponseDTO<ProdutoDTO>listarProdutos();
    ResponseDTO<ProdutoDTO> obterProdutosNome(String nome);
    ResponseDTO<ProdutoDTO> atualizarProduto(ProdutoDTO produtoDTO);
    ResponseDTO<ProdutoDTO>deletarProduto(Long id);

}
