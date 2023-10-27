package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/produtos")
public class ProdutoResource extends BaseResource {

    @Autowired
    ProdutoService produtoService;


    @PostMapping("/inserir")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> inserirProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) createdCodeReturn(produtoService.inserirProduto(produtoDTO));
    }

    @GetMapping(value = "/produtos")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> listarProdutos(@RequestParam (required = false) @Valid String nome) {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) list(produtoService.listarProdutos(nome));
    }
    @GetMapping(value = "/produtosnome")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> obterProdutosNome(@RequestParam @Valid String nome) {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) list(produtoService.obterProdutosNome(nome));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
