package br.com.lojapesca.lojadepesca.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrinho {

    @Id
    @Column(name = "id_Carrinho")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "produto_item")
    @Nullable
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<Pedido> pedido = new ArrayList<>();
    @Nullable
    private Integer quantidade;
    @Column(name = "valor_total")
    @Nullable
    private Double valorTotal;
}
