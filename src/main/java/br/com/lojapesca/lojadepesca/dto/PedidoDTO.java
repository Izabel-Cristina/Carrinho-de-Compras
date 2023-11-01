package br.com.lojapesca.lojadepesca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @JsonIgnore
    private Long idPedido;
    @NotBlank
    private ProdutoDTO produtoDTO;
    @NotNull
    private Integer quantidade;
    @NotNull
    private CarrinhoDTO CarrinhoDTO;
}
