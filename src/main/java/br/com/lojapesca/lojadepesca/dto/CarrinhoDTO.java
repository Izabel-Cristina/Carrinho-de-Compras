package br.com.lojapesca.lojadepesca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 1, message = "Acrescente no mínimo um produto.")
    private List<ItemDTO> itens;
    @Min(value = 1, message = "acrescente no minimo uma unidade.")
    private Integer quantidadeProduto;
    private Double precoTotalProduto;


}

