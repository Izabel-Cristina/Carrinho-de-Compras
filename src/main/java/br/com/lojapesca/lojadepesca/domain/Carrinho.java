package br.com.lojapesca.lojadepesca.domain;

import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrinho", catalog = "loja_pesca")
public class Carrinho implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_carrinho")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
//    @JoinColumn(name = "id_carrinho", referencedColumnName = "id_carrinho")
//    private List<Item> itens = new ArrayList<>();
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @Nullable
    private Integer quantidade;
    @Column(name = "valor_total")
    @Nullable
    private Double valorTotal;

}
