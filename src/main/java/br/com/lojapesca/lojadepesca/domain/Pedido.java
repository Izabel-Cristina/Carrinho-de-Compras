package br.com.lojapesca.lojadepesca.domain;

import jakarta.annotation.Nullable;
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
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_pedido" )
    private Long idPedido;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    @Nullable
    private Produto produto;
    @Column(name = "quantidade_produto")
    @Nullable
    private Integer quantidadeProduto;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;
}