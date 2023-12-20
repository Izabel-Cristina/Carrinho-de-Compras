package br.com.lojapesca.lojadepesca.domain;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto", catalog = "loja_pesca")
public class Produto implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;
    @Column
    @Nullable
    private String nome;
    @Column
    @Nullable
    private String descricao;
    @Column
    @Nullable
    private Double preco;

    public Produto(String nome, String descricao, Double preco) {
    }
}
