package br.com.lojapesca.lojadepesca.mapper;

import br.com.lojapesca.lojadepesca.domain.Item;
import br.com.lojapesca.lojadepesca.domain.Produto;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

}
