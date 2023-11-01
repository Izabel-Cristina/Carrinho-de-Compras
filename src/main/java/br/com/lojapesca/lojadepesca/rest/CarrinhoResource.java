package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/carrinhos")
@Tag(name = "Carrinho ")
public class CarrinhoResource extends BaseResource {

    @Autowired
    CarrinhoService carrinhoService;

    @Operation(summary = "Listar e realizar o calculo do valor total dos produtos que estão dentro do carrinho(Resumo do carrinho)", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
    @GetMapping("/carrinho")
    public ResponseEntity<ResponseDTO> carrinho(@RequestBody @Valid CarrinhoDTO carrinhoDTO) {
        return (ResponseEntity<ResponseDTO>) createdCodeReturn(carrinhoService.carrinho(carrinhoDTO));
    }

    //Resource usado para realizar as validações com a dependencia validation
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