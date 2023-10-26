package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/produtos")
public class ProdutoResource extends BaseResource {

    @Autowired
    ProdutoService produtoService;


    @PostMapping("/inserir")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> inserirProduto(@RequestBody ProdutoDTO produtoDTO) {
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) createdCodeReturn(produtoService.inserirProduto(produtoDTO));
    }

    @GetMapping(value = "/produtos")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> listarProdutos(@RequestParam(required = false) String nome) {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) list(produtoService.listarProdutos(nome));
    }
    @GetMapping(value = "/produtosnome")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> obterProdutosNome(@RequestParam String nome) {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) list(produtoService.obterProdutosNome(nome));
    }
}
