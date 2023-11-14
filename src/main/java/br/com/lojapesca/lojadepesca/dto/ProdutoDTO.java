package br.com.lojapesca.lojadepesca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
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
    private Long idProduto;
    @NotBlank(message = "O campo 'Nome' está vazio")
    @Column(unique = true)
    private String nome;
    @NotBlank(message = "O campo 'Descrição' está vazio")
    @Column()
    private String descricao;
    @Min(value = 1, message = "O campo preço não pode ser menor que 1")
    private Double preco;

    public ProdutoDTO(String nome, String descricao, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
}