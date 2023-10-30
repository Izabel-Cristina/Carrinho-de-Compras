package br.com.lojapesca.lojadepesca.bo;


import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.PedidoDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CarrinhoBo {

    public CarrinhoDTO adicionarProdutoCarrinho(CarrinhoDTO carrinhoDTO) throws Exception {
        String mensagem = "";
        String descricao = "";
        Double preco = 0.00;
        Integer quantidadeTotal = 0;
        Double precoTotalCarrinho = 0.00;

        for (PedidoDTO item : carrinhoDTO.getPedido()) {
            String nome = item.getProdutoDTO().getNome();
            Integer quantidade = item.getQuantidade();
            if (item.getProdutoDTO().getNome().trim().isEmpty() || item.getProdutoDTO().equals("")) {
                throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
            }
            ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();
            try {
                String sql = "SELECT descricao, preco FROM produto WHERE nome = ?";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setString(1, nome);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    preco = resultSet.getDouble("preco");
                    descricao = resultSet.getString("descricao");

                    item.getProdutoDTO().setDescricao(descricao);
                    item.getProdutoDTO().setPreco(preco);
                    item.getQuantidade();

                    Double valorTotalItem = preco * quantidade;
                    //item.setQuantidade((int) valorTotalItem);
                    quantidadeTotal += quantidade;
                    precoTotalCarrinho += valorTotalItem;

                } else if (preco == null || preco == 0D) {
                    throw new Exception("Verifique o produto por favor");
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            carrinhoDTO.setPrecoTotalProduto(precoTotalCarrinho);
            carrinhoDTO.setQuantidadeProduto(quantidadeTotal);

        }
        return carrinhoDTO;
    }
}

//    public CarrinhoDTO calcularValorProduto(CarrinhoDTO carrinhoDTO) throws Exception {
//        carrinhoDTO.setPedido((List<PedidoDTO>) adicionarProdutoCarrinho(carrinhoDTO));
//        carrinhoDTO.setQuantidadeProduto(carrinhoDTO.getQuantidadeProduto());
//
//    }
//}

//        carrinhoDTO.getProdutoDTO().getNome();
//        String nome = carrinhoDTO.getProdutoDTO().getNome();
//
//        String descricao = "";
//        Double preco = 0.00;
//        String mensagem = "";
//        if (carrinhoDTO.getProdutoDTO().getNome().trim().isEmpty() || carrinhoDTO.getProdutoDTO().getNome().equals("")) {
//            throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
//
//        } else {
//            ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();
//
//            try {
//                String sql = "SELECT preco, descricao FROM produto WHERE nome = ?";
//                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
//                statement.setString(1, nome);
//
//                ResultSet resultSet = statement.executeQuery();
//
//                if (resultSet.next()) {
//                    descricao = resultSet.getString("descricao");
//                    preco = resultSet.getDouble("preco");
//                } else if (preco == 0.0) {
//                    preco.toString(Double.parseDouble("produto não encontrado!"));
//                }
//                resultSet.close();
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

//            Integer quantidade = carrinhoDTO.getQuantidadeProduto();
//            Double precoUnitario = preco;
//            carrinhoDTO.getProdutoDTO().setDescricao(descricao);
//            carrinhoDTO.getProdutoDTO().setPreco(precoUnitario);
//
//            Double precoTotal = quantidade * precoUnitario;
//            carrinhoDTO.setPrecoTotalProduto(precoTotal);
//
//            Produto produto = new Produto();
//            produto.setNome(carrinhoDTO.getProdutoDTO().getNome());
//            produto.setDescricao(carrinhoDTO.getProdutoDTO().getDescricao());
//            produto.setPreco(carrinhoDTO.getProdutoDTO().getPreco());
//
//            try {
//                carrinhoDTO.getProdutoDTO();
//                carrinhoDTO.getPrecoTotalProduto();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return carrinhoDTO;
//        }
//    }
//}