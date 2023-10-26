package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.CarrinhoService;
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
@RequestMapping(value = "api/v1/carrinhos")
public class CarrinhoResource extends BaseResource {

    @Autowired
    CarrinhoService carrinhoService;

    @GetMapping("/carrinho")
    public ResponseEntity<ResponseDTO> carrinho(@RequestBody @Valid CarrinhoDTO carrinhoDTO) {
        List<Produto> produtos = new ArrayList<>();
        return (ResponseEntity<ResponseDTO>) list(carrinhoService.carrinho(carrinhoDTO));
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