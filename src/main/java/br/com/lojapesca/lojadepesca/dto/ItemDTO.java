package br.com.lojapesca.lojadepesca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long idItem;
    @NotNull(message = "Necessário incluir pelo menos um produto!")
    private ProdutoDTO produtoDTO;
    @Min(value = 1, message = "")
    private Integer quantidade;
    private Double valorTotal;

}
