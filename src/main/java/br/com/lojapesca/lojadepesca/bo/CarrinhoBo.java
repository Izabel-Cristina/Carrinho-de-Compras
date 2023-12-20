package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Carrinho;
import br.com.lojapesca.lojadepesca.domain.Item;
import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;

import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.mapper.ItemMapper;
import br.com.lojapesca.lojadepesca.mapper.ProdutoMapper;
import br.com.lojapesca.lojadepesca.repository.CarrinhoRepository;
import br.com.lojapesca.lojadepesca.repository.ItemRepository;
import br.com.lojapesca.lojadepesca.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Component
public class CarrinhoBo {

    @Autowired
    ItemBO itemBO;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CarrinhoRepository carrinhoRepository;
    private ProdutoMapper produtoMapper;
    private ProdutoRepository produtoRepository;

    public CarrinhoBo(ProdutoMapper produtoMapper, CarrinhoRepository carrinhoRepository,
                      ItemMapper itemMapper, ProdutoRepository produtoRepository,
                      ItemRepository itemRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoMapper = produtoMapper;
        this.itemMapper = itemMapper;
        this.produtoRepository = produtoRepository;
        this.itemRepository = itemRepository;
    }

    ConexaoBancoBo conexaoBancoBo = new ConexaoBancoBo();


    public CarrinhoDTO adicionarProdutoCarrinho(CarrinhoDTO carrinhoDTO) throws Exception {
        Double preco = 0.00;
        Integer quantidade = 0;
        Long id = null;

        ProdutoBo produtoBo = new ProdutoBo();
        Carrinho carrinho = new Carrinho();
        List<Item> itensCarrinho = new ArrayList<>();
        Integer quantidadeCarrinho = 0;
        Double valorCarrinho = 0.00;
        Double valorTotal = 0.00;
        Integer quantidadeItem = 0;
        Double valorCarrinhoTotal = 0.00;

        Map<Long, Item> mapaItensPorId = new HashMap<>();

        for (ItemDTO itemDTO : carrinhoDTO.getItens()) {
            String nome = itemDTO.getProdutoDTO().getNome();

            id = itemDTO.getProdutoDTO().getIdProduto();
            itemBO.adicionarItens(itemDTO);

            ProdutoDTO produtoDTO = itemDTO.getProdutoDTO();
            // Verifique se o produtoDTO está presente
            if (produtoDTO != null) {
                // Verifique se o nome do produto não está vazio ou nulo
                if (!produtoDTO.getNome().trim().isEmpty()) {
                    // Configure os atributos do item
                    quantidade = itemDTO.getQuantidade();
                    preco = produtoDTO.getPreco();
                    id = produtoDTO.getIdProduto();
                    valorTotal = itemDTO.getValorTotal();

                    // Busque o produto existente do banco de dados
                    Produto produto = produtoRepository.findById(id).orElseThrow(() -> new Exception("Produto não encontrado para o id: "));

                    Item item = new Item();

                    item.setCarrinho(carrinho);
                    item.setProduto(produto);

                    item.setQuantidadeProduto(quantidade);
                    item.setValorTotal(valorTotal);


                    quantidadeItem = quantidadeCarrinho += itemDTO.getQuantidade();
                    carrinho.setQuantidade(quantidadeItem);
                    valorCarrinhoTotal = valorCarrinho += valorTotal;
                    carrinho.setValorTotal(valorCarrinhoTotal);


                    if (mapaItensPorId.containsKey(id)) {
                        // Atualizar quantidade e valor se o produto já estiver no carrinho
                        Item itemExistente = mapaItensPorId.get(id);
                        itemExistente.setQuantidadeProduto(itemExistente.getQuantidadeProduto() + quantidade);
                        itemExistente.setValorTotal(itemExistente.getValorTotal() + valorTotal);
                    } else {

                        // Adicionar item ao mapa para rastrear por ID do produto
                        mapaItensPorId.put(id, item);

                        itensCarrinho.add(item);
                    }
                } else {
                    throw new Exception("Erro: Verifique as informações do produto.");
                }
            } else {
                throw new Exception("Erro: Produto não especificado para o item.");
            }
            carrinhoDTO.setQuantidadeProduto(quantidadeItem);
            carrinhoDTO.setPrecoTotalProduto(valorCarrinho);
        }


        // Associe a lista de itens ao carrinho
        carrinho.setItens(itensCarrinho);

        // Salve o carrinho no repositório
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

        return carrinhoDTO;
    }


    public List<CarrinhoDTO> listarCarrinho() throws Exception {
        List<CarrinhoDTO> carrinhos = new ArrayList<>();


        try {
            // Mapear a lista de Optionals para uma lista de ItemDTO
            for (Carrinho carrinho : carrinhoRepository.findAll()) {
                CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
                Long idCarrinho = carrinho.getId();
                carrinhoDTO.setId(idCarrinho);

                carrinhoDTO.setPrecoTotalProduto(carrinho.getValorTotal());
                carrinhoDTO.setQuantidadeProduto(carrinho.getQuantidade());

                // Obter dados do item para o carrinho atual
                List<Optional<ItemDTO>> itensDoCarrinho = itemBO.obterDadosIdCarrinho(idCarrinho);

                // Mapear a lista de Optionals para uma lista de ItemDTO
                List<ItemDTO> itensList = itensDoCarrinho.stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

                // Adicionar itens ao carrinho (se existirem)
                if (!itensList.isEmpty()) {
                    carrinhoDTO.setItens(itensList);
                } else {
                    // Lançar uma exceção ou tratar de acordo com a lógica desejada quando não há itens
                    throw new Exception();
                }


                // Adicionar o CarrinhoDTO à lista
                carrinhos.add(carrinhoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carrinhos;
    }

    public List<CarrinhoDTO> listarCarrinhoId(Long id) throws Exception {
        if (id.equals(null) || id.describeConstable().isEmpty() || id.equals("") || id.equals(" ") ||id ==null) {
            throw new Exception();
        } else {
            List<CarrinhoDTO> carrinhos = new ArrayList<>();
            CarrinhoDTO carrinhoDTO = new CarrinhoDTO();


            try {
                Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
                carrinhoDTO.setId(carrinho.get().getId());
                carrinhoDTO.setPrecoTotalProduto(carrinho.get().getValorTotal());
                carrinhoDTO.setQuantidadeProduto(carrinho.get().getQuantidade());

                // Obter dados do item para o carrinho atual
                List<Optional<ItemDTO>> itensDoCarrinho = itemBO.obterDadosIdCarrinho(id);

                // Mapear a lista de Optionals para uma lista de ItemDTO
                List<ItemDTO> itensList = itensDoCarrinho.stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

                // Adicionar itens ao carrinho (se existirem)
                if (!itensList.isEmpty()) {
                    carrinhoDTO.setItens(itensList);
                }

                // Adicionar o CarrinhoDTO à lista
                carrinhos.add(carrinhoDTO);
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
            return carrinhos;
        }
    }
}


