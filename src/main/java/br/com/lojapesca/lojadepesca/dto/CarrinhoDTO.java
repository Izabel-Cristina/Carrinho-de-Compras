package br.com.lojapesca.lojadepesca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {
    @JsonIgnore
    private Long id;
    @NotNull
    @Size(min = 1, message = "Acrescente no mínimo um produto.")
    private List<ItemDTO> itens;
    @Min(value = 1, message = "acrescente no minimo uma unidade.")
    private Integer quantidadeProduto;
    private Double precoTotalProduto;
    public CarrinhoDTO(List<ItemDTO>itens, Integer quantidadeProduto, Double precoTotalProduto){
        this.itens = itens;
        this.quantidadeProduto =  quantidadeProduto;
        this.precoTotalProduto = precoTotalProduto;
    }
}
