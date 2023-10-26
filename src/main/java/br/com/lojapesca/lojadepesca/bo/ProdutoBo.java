package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import jakarta.validation.Valid;
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

        if (produtoDTO.getNome().trim().isEmpty() || produtoDTO.getNome().equals("")) {
            throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
        } else if (produtoDTO.getDescricao().trim().isEmpty() || produtoDTO.getDescricao().equals("")) {
            throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
        } else if (produtoDTO.getPreco() <= 0) {
            throw new Exception(mensagem = "Erro: Preço do produto não pode ser zero.");

        } else {
            try {
                // Preparar a consulta SQL de inserção
                String sql = "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?,?)";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setString(1, nome);
                statement.setString(
                        2, descricao);
                statement.setDouble(3, preco);


                // Executar a inserção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    mensagem = "Produto incluído com sucesso.";
                }
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                // Em caso de exceção, você pode configurar uma mensagem de erro apropriada
                mensagem = "Erro ao salvar o Produto: ";
            }
            return mensagem;
        }
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

    public List<Produto> obterProdutosNome(String nome){
        List<Produto> produtos = new ArrayList<Produto>();
        String mensagem = "";
        try {
            String sql = "SELECT nome, descricao, preco FROM produto WHERE nome Like ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            statement.setString(1,"%" + nome + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nomeProduto = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                Double preco = resultSet.getDouble("preco");

                Produto produto = new Produto(nomeProduto,descricao, preco);
                produtos.add(produto);
            }

            if (produtos.isEmpty()|| nome.equals("")||nome.trim().isEmpty()) {
// Se a lista de produtos estiver vazia, você pode adicionar um produto especial "não encontrado".
                mensagem = "Produto não encontrado!";
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}




