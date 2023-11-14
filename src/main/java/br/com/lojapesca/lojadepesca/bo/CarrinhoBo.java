package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Carrinho;
import br.com.lojapesca.lojadepesca.domain.Item;
import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.repository.CarrinhoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class CarrinhoBo {


    ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();

    public CarrinhoDTO adicionarProdutoCarrinho(CarrinhoDTO carrinhoDTO) throws Exception {
        String mensagem = "";
        String descricao = "";
        Double preco = 0.00;
        Integer quantidade = 0;

        Double valorTotalItem = 0.00;
        Long id = null;
        Long idCarrinho = null;
        ItemBO itemBO = new ItemBO();
        ProdutoBo produtoBo = new ProdutoBo();
        Integer quantidadeItem = null;
        Double valorTotalCarrinho = null;
        Long idItem = null;
        Double valorUnitario = 0.00;


        for (ItemDTO item : carrinhoDTO.getItens()) {
            String nome = item.getProdutoDTO().getNome();


            idItem = item.getIdItem();

            if (item.getProdutoDTO().getNome().trim().isEmpty() || item.getProdutoDTO().equals("")) {
                throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
            }

            ItemDTO itemDTO = itemBO.obterDadosItemId(idItem);

            preco = itemDTO.getValorTotal();
            quantidade = itemDTO.getQuantidade();


            try {
                String sql = "SELECT id_produto, id_carrinho, quantidade_produto, valor_total_item FROM item WHERE id_item = ?";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setLong(1, idItem);
                ResultSet resultSet = statement.executeQuery();
                id = item.getProdutoDTO().getIdProduto();


                if (resultSet.next()) {
                    statement.setLong(1, id);
                    id = resultSet.getLong("id_produto");
                    idCarrinho = resultSet.getLong("id_carrinho");

                    preco = resultSet.getDouble("valor_total_item");
                    quantidade = resultSet.getInt("quantidade_produto");
                    item.getProdutoDTO().getIdProduto();




                    valorTotalItem += preco;
                    valorTotalCarrinho = valorTotalItem * quantidade;


                    if (idCarrinho == (0) || (idCarrinho.longValue() == 0L) || idCarrinho.equals(null)) {
                        try {
                            String sqlInsercao = "INSERT INTO carrinho (quantidade, valor_total) VALUES (?, ?)";
                            PreparedStatement statementInsercao = conexaoBancoBo.getConnection().prepareStatement(sqlInsercao);
                            statementInsercao.setInt(1, quantidade);
                            statementInsercao.setDouble(2, valorTotalCarrinho);


                            int rowsAffected = statementInsercao.executeUpdate();


                            if (rowsAffected > 0) {
                                mensagem = "Produto incluído com sucesso.";


                                String sqlItem = "SELECT id_carrinho FROM item WHERE id_item = ?";
                                PreparedStatement statement1 = conexaoBancoBo.getConnection().prepareStatement(sqlItem);
                                statement.setLong(1, idItem);

                                ResultSet resultSet1 = statement.executeQuery();
                                if (resultSet1.next()) {
                                    idCarrinho = resultSet1.getLong("id_carrinho");

                                try {
                                    String sqlAtualizar = "UPDATE item SET id_carrinho = ? WHERE id_item= ?";
                                    PreparedStatement statementUpdate = conexaoBancoBo.getConnection().prepareStatement(sqlAtualizar);
                                    statementUpdate.setLong(1, idCarrinho);
                                    statementUpdate.setLong(1, idItem);
                                    statementUpdate.close();


                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }

                                statementInsercao.close();
                        }
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                        carrinhoDTO.setQuantidadeProduto(quantidade);
                        carrinhoDTO.setPrecoTotalProduto(valorTotalItem);






                } else {
                    try {

                        sql = "UPDATE carrinho SET quantidade = ?, valor_total = ? WHERE id_carrinho = ?";
                        PreparedStatement statementUpdate = conexaoBancoBo.getConnection().prepareStatement(sql);
                        statementUpdate.setInt(1, quantidade);
                        statementUpdate.setDouble(2, preco);
                        statementUpdate.setLong(3, id);

                        int executarAtualizacao = statementUpdate.executeUpdate();
                        if (executarAtualizacao > 0) {
                        }
                        resultSet.close();
                        statementUpdate.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
        carrinhoDTO.setQuantidadeProduto(quantidade);
        carrinhoDTO.setPrecoTotalProduto(valorTotalCarrinho);


        return carrinhoDTO;
}

    public List<CarrinhoDTO> listarCarrinho() {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();

        try {
            String sql = "SELECT * FROM carrinho";

            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<ItemDTO> itens = (List<ItemDTO>) resultSet.getObject("itens");
                Integer quantidade = resultSet.getInt("quantidade");
                Double preco = resultSet.getDouble("valor_total");

                // Crie um objeto Produto com os dados do ResultSet
                carrinhoDTO = new CarrinhoDTO(itens, quantidade, preco);

                Carrinho carrinho = new Carrinho();
                carrinho.add(carrinhoDTO);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (List<CarrinhoDTO>) carrinhoDTO;
    }
}
