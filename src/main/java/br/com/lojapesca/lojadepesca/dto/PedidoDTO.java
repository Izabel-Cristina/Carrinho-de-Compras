package br.com.lojapesca.lojadepesca.dto;

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
    private Long id;
    @NotBlank
    private ProdutoDTO produtoDTO;
    @NotNull
    private Integer quantidade;
    @NotNull
    private CarrinhoDTO CarrinhoDTO;
}
