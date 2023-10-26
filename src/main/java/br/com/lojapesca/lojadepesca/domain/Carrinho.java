package br.com.lojapesca.lojadepesca.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Produto produto;
    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;
    @Column(name = "preco_unitario")
    private Double precoUnitarioProduto;
    @Column(name = "valor_total")
    private Double precoTotalProduto;
}
