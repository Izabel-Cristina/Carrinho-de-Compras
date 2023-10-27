package br.com.lojapesca.lojadepesca.bo;


import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class CarrinhoBo {

    public CarrinhoDTO adicionarProdutoCarrinho(CarrinhoDTO carrinhoDTO) throws Exception {

        carrinhoDTO.getProduto().getNome();
        String nome = carrinhoDTO.getProduto().getNome();

        String descricao = "";
        Double preco = 0.00;
        String mensagem = "";
        if (carrinhoDTO.getProduto().getNome().trim().isEmpty() || carrinhoDTO.getProduto().getNome().equals("")) {
            throw new Exception(mensagem = "Erro: Verifique as informações do produto.");

        } else {
            ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();

            try {
                String sql = "SELECT preco, descricao FROM produto WHERE nome = ?";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setString(1, nome);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    descricao = resultSet.getString("descricao");
                    preco = resultSet.getDouble("preco");
                } else if (preco == 0.0) {
                    preco.toString(Double.parseDouble("produto não encontrado!"));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Integer quantidade = carrinhoDTO.getQuantidadeProduto();
            Double precoUnitario = preco;
            carrinhoDTO.getProduto().setDescricao(descricao);
            carrinhoDTO.getProduto().setPreco(precoUnitario);

            Double precoTotal = quantidade * precoUnitario;
            carrinhoDTO.setPrecoTotalProduto(precoTotal);

            Produto produto = new Produto();
            produto.setNome(carrinhoDTO.getProduto().getNome());
            produto.setDescricao(carrinhoDTO.getProduto().getDescricao());
            produto.setPreco(carrinhoDTO.getProduto().getPreco());

            try {
                carrinhoDTO.getProduto();
                carrinhoDTO.getPrecoTotalProduto();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return carrinhoDTO;
        }
    }
}
