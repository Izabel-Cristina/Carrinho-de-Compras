package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProdutoBo {
    ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();

    public String inserirProduto(ProdutoDTO produtoDTO) throws Exception {
        String nome = produtoDTO.getNome();
        String descricao = produtoDTO.getDescricao();
        Double preco = produtoDTO.getPreco();
        String mensagem = "";

        try {
            String sql = "SELECT nome, descricao FROM produto WHERE nome = ? OR descricao = ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, descricao);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nomeEncontrado = resultSet.getString("nome");
                String descricaoEncontrada = resultSet.getString("descricao");

                if (nome.equalsIgnoreCase(nomeEncontrado) || descricao.equalsIgnoreCase(descricaoEncontrada)) {
                    throw new Exception();
                }
                resultSet.close();// Fechando o ResultSet
                statement.close();// Fechando o PreparedStatement de inserção
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sqlInsercao = "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?, ?)";
            PreparedStatement statementInsercao = conexaoBancoBo.getConnection().prepareStatement(sqlInsercao);
            statementInsercao.setString(1, nome);
            statementInsercao.setString(2, descricao);
            statementInsercao.setDouble(3, preco);

            int rowsAffected = statementInsercao.executeUpdate();

            if (rowsAffected > 0) {
                mensagem = "Produto incluído com sucesso.";
            }
            statementInsercao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagem;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            ProdutoDTO produtoDTO = new ProdutoDTO();

            String sql = "SELECT * FROM produto";

            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                Double preco = resultSet.getDouble("preco");

                // Crie um objeto Produto com os dados do ResultSet
                Produto produto = new Produto(nome, descricao, preco);

                // Adicione o produto à lista de produtos
                produtos.add(produto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public List<Produto> obterProdutosNome(String nome) throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        String mensagem = "";
        Double preco = 0.00;
        String nomeProduto = "";
        try {
            String sql = "SELECT nome, descricao, preco FROM produto WHERE nome Like ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                nomeProduto = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                preco = resultSet.getDouble("preco");

                Produto produto = new Produto(nomeProduto, descricao, preco);
                produtos.add(produto);
            }

            if (produtos.isEmpty() || nome.equals("") || nome.trim().isEmpty()) {
// Se a lista de produtos estiver vazia, você pode adicionar um produto especial "não encontrado".
                throw new Exception();
            } else if (produtos.size() == 0) {
                throw new Exception();
            } else {
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}





