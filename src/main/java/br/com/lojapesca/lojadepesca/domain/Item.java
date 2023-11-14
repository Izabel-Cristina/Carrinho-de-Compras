package br.com.lojapesca.lojadepesca.domain;

import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item", catalog = "loja_pesca")
public class Item implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    @Nullable
    private Produto produto;
    @Column(name = "quantidade_produto")
    @Nullable
    private Integer quantidadeProduto;

    @Column(name = "valor_total_item")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id_carrinho")
    private Carrinho carrinho;
}