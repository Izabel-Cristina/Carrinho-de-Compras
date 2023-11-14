package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/item")
@Tag(name = "CarrinhoItens ")
public class ItemResource extends BaseResource{

    @Autowired
    ItemService itemService;
    @PostMapping("/itemcarrinho")
    public ResponseEntity<ResponseDTO<ItemDTO>>inserirItem(@RequestBody @Valid ItemDTO itemDTO){
        return (ResponseEntity<ResponseDTO<ItemDTO>>) createdCodeReturn(itemService.inserirItem(itemDTO));
    }

    @DeleteMapping("/deletaritem")
    public ResponseEntity<ResponseDTO<ItemDTO>>removerItem(@RequestParam(required = false)Long idProduto){
        return (ResponseEntity<ResponseDTO<ItemDTO>>) deleteCodeReturn(itemService.removerItem(idProduto));
    }

    @PatchMapping("/atualizarquantidadeitem")
    public ResponseEntity<ResponseDTO<ItemDTO>>atualizarQuantidadeItem(@RequestBody ItemDTO itemDTO){
        return (ResponseEntity<ResponseDTO<ItemDTO>>) updatedCodeReturn(itemService.atualizarQuantidadeItem(itemDTO));
    }
}
