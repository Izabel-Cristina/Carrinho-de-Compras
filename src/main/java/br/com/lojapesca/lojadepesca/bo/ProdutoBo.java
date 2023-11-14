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
        String nome = produtoDTO.getNome().trim();
        String descricao = produtoDTO.getDescricao().trim();
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

    public String atualizarProduto(ProdutoDTO produtoDTO) throws Exception {
        String mensagem = "";
        Long id = produtoDTO.getIdProduto();
        String nome = produtoDTO.getNome().trim();
        String descricao = produtoDTO.getDescricao().trim();
        Double preco = produtoDTO.getPreco();

        if (nome.trim().isEmpty() || nome.equals("") || descricao.trim().isEmpty() || descricao.equals("") || preco <= 0 || preco == null) {
            throw new Exception();
        } else {

            try {
                String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ? WHERE id_produto = ?";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setString(1, nome);
                statement.setString(2, descricao);
                statement.setDouble(3, preco);
                statement.setLong(4, id);

                int executarAtualizacao = statement.executeUpdate();

                if (executarAtualizacao > 0) {
                    mensagem = "Produto atualizado com sucesso";
                } else {
                    mensagem = "Nenhum produto foi atualizado!";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                mensagem = "Erro ao tentar atualizar o produto!";

            }
            return mensagem;
        }
    }


    public String deletarProduto(Long idProduto) throws Exception {
        String mensagem = "";

        if (idProduto.equals(0) || idProduto.equals(null) || idProduto.toString().trim().isEmpty() || idProduto.toString().isBlank()) {
            throw new Exception();
        } else {

            try {
                String sql = "DELETE FROM produto WHERE id_produto = ?";
                PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
                statement.setLong(1, idProduto);

                int executarExclusao = statement.executeUpdate();

                if (executarExclusao > 0) {
                    mensagem = "Produto excluído com sucesso!";
                } else {
                    throw new Exception();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                mensagem = "Erro ao tentar excluir o produto!";
            }
        }
        return mensagem;

    }

    public List<Produto> obterProdutosId(Long id) throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        String mensagem = "";
        Double preco = 0.00;
        String nomeProduto = "";
        try {
            String sql = "SELECT nome, descricao, preco FROM produto WHERE idProduto Like ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                nomeProduto = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                preco = resultSet.getDouble("preco");

                Produto produto = new Produto(nomeProduto, descricao, preco);
                produtos.add(produto);
            }

            if (produtos.isEmpty() || id.equals("") || id.equals(null)) {
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

    public ProdutoDTO obterDadosProdutoId(Long id) throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        try {
            String sqlSelect = "SELECT nome, descricao, preco FROM produto WHERE id_produto Like ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sqlSelect);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nomeProduto = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                Double preco = resultSet.getDouble("preco");

                produtoDTO.setNome(nomeProduto);
                produtoDTO.setDescricao(descricao);
                produtoDTO.setPreco(preco);
            }
            if (id.equals("") || id.equals(null)) {
// Se a lista de produtos estiver vazia, você pode adicionar um produto especial "não encontrado".
                throw new Exception();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtoDTO;
    }
}




