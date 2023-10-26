package br.com.lojapesca.lojadepesca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    @NotBlank(message = "O campo 'Nome' está vazio")
    private String nome;
    @NotBlank(message = "O campo 'Descrição' está vazio")
    private String descricao;
    @NotNull(message = "O campo 'Preço' está vazio")
    private Double preco;

    public ProdutoDTO(String nome, String descricao, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
}