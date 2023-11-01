package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/produtos")
@Tag(name = "Produto")
public class ProdutoResource extends BaseResource {

    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Adicionar produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
    @PostMapping("/inserir")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> inserirProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) createdCodeReturn(produtoService.inserirProduto(produtoDTO));
    }


    @Operation(summary = "Listar todos produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
    @GetMapping(value = "/produtos")
    public ResponseEntity<ResponseDTO<ProdutoDTO>> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO<ProdutoDTO>>) list(produtoService.listarProdutos());
    }

    @Operation(summary = "Filtra o produto por nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView usuarioExiste(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("nome", "Usuário já existe!");
        return new ModelAndView("redirect:api/v1/produtos/inserir");
    }
}
