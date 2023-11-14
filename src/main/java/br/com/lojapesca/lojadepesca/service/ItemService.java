package br.com.lojapesca.lojadepesca.service;

import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;

public interface ItemService {
    ResponseDTO<ItemDTO> inserirItem(ItemDTO itemDTO);
    ResponseDTO<ItemDTO>removerItem(Long idProduto);
    ResponseDTO<ItemDTO>atualizarQuantidadeItem(ItemDTO itemDTO);
}
