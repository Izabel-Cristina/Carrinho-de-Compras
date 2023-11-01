package br.com.lojapesca.lojadepesca.dto;

import br.com.lojapesca.lojadepesca.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Min(value = 1, message = "acrescente no minimo um produto.")
    private List<PedidoDTO> pedido;
    @Min(value = 1, message = "acrescente no minimo uma unidade.")
    private Integer quantidadeProduto;
    private Double precoTotalProduto;
}