package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ItemBO {
    private final ProdutoRepository produtoRepository;

    public ItemBO(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();

    public ItemDTO adicionarItens(ItemDTO itemDTO) throws Exception {
        String nome = itemDTO.getProdutoDTO().getNome().trim();
        Integer quantidadeProduto = itemDTO.getQuantidade();
        Double preco = 0.00;
        Long id = null;
        Long idItem = null;
        String descricao = "";
        Double valorTotalItem = 0.00;
        Double precoTotalCarrinho = 0.00;
        Integer quantidadeTotal = 0;
        Integer quantidade = itemDTO.getQuantidade();
        String mensagem = "";
        Integer adicionarQuantidade = 0;
        Double precoTotal = 0.00;


        if (itemDTO.getProdutoDTO().getNome().trim().isEmpty() || itemDTO.getQuantidade().equals(null)) {
            throw new Exception(mensagem = "Erro: Verifique as informações do produto.");
        }

        try {
            String sql = "SELECT id_produto, descricao, preco FROM produto WHERE nome = ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sql);
            statement.setString(1, nome);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getLong("id_produto");
                preco = resultSet.getDouble("preco");
                descricao = resultSet.getString("descricao");

                itemDTO.getProdutoDTO().setDescricao(descricao);

                itemDTO.getProdutoDTO().setIdProduto(id);

                valorTotalItem += preco;

                quantidadeTotal += quantidade;

                quantidadeTotal = itemDTO.getQuantidade();
                precoTotal = preco * quantidade;

                itemDTO.getProdutoDTO().setPreco(preco);

            } else if (precoTotal == null || precoTotal == 0D) {
                throw new Exception();
            }
            //
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        itemDTO.setValorTotal(precoTotal);

        return itemDTO;
    }

    public String removerItem(Long idProduto) throws Exception {

        String mensagem = "";
        if (idProduto.equals(0) || idProduto.equals(null) || idProduto.toString().trim().isEmpty() || idProduto.toString().isBlank()) {
            throw new Exception();
        } else {

            try {
                String sql = "DELETE FROM item WHERE id_produto = ?";
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

    public ItemDTO atualizarQuantidadeItem(ItemDTO itemDTO) throws Exception {

        Integer quantidade = itemDTO.getQuantidade();
        Long idProduto = itemDTO.getProdutoDTO().getIdProduto();

        Long idProdutoAt = null;
        Long idItemAt = null;

        String sqlSelect = "SELECT id_item, id_produto FROM item WHERE id_produto = ?";
        PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sqlSelect);
        statement.setLong(1, itemDTO.getProdutoDTO().getIdProduto());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {

            idProdutoAt = resultSet.getLong("id_produto");

            idItemAt = resultSet.getLong("id_item");

            if (!idItemAt.equals(null)) {
                try {
                    String sqlItemAt = "UPDATE Item SET quantidade_produto = ? WHERE id_produto = ?";
                    PreparedStatement statementUpdate = conexaoBancoBo.getConnection().prepareStatement(sqlItemAt);
                    statementUpdate.setInt(1, quantidade);
                    statementUpdate.setLong(2, idProdutoAt);

                    int executarAtualizacao = statementUpdate.executeUpdate();
                    if (executarAtualizacao > 0) {

                        resultSet.close();
                        statementUpdate.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            throw new Exception();
        }
        return itemDTO;
    }

    public ItemDTO obterDadosItemId(Long id) throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        try {
            String sqlSelect = "SELECT id_produto, id_carrinho, quantidade_produto,valor_total_item FROM item WHERE id_item Like ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sqlSelect);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long idProduto = resultSet.getLong("id_produto");
                Long idCarrinho = resultSet.getLong("id_carrinho");
                Integer quantidadeProduto = resultSet.getInt("quantidade_produto");
                Double valorTotalItem = resultSet.getDouble("valor_total_item");

                itemDTO.setQuantidade(quantidadeProduto);
                itemDTO.setValorTotal(valorTotalItem);
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
        return itemDTO;
    }

    public List<Optional<ItemDTO>> obterDadosIdCarrinho(Long id) throws Exception {

        List<Optional<ItemDTO>> lista = new ArrayList<>();

        try {
            String sqlSelect = "SELECT id_produto, quantidade_produto, valor_total_item FROM item WHERE id_carrinho = ?";
            PreparedStatement statement = conexaoBancoBo.getConnection().prepareStatement(sqlSelect);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long idProduto = resultSet.getLong("id_produto");
                Integer quantidadeProduto = resultSet.getInt("quantidade_produto");
                Double valorTotalItem = resultSet.getDouble("valor_total_item");

                Optional<Produto> produtoDT = produtoRepository.findById(idProduto);

                if (produtoDT.isPresent()) {
                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setIdProduto(idProduto);
                    produtoDTO.setNome(produtoDT.get().getNome());
                    produtoDTO.setDescricao(produtoDT.get().getDescricao());
                    produtoDTO.setPreco(produtoDT.get().getPreco());

                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setQuantidade(quantidadeProduto);
                    itemDTO.setValorTotal(valorTotalItem);
                    itemDTO.setProdutoDTO(produtoDTO);

                    lista.add(Optional.of(itemDTO));
                } else {
//                     Se o resultado estiver vazio, você pode lançar uma exceção ou retornar null, dependendo do seu requisito.
                    throw new Exception();
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}





