package br.com.lojapesca.lojadepesca.mapper;

import br.com.lojapesca.lojadepesca.domain.Carrinho;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", uses = {})
public interface CarrinhoMapper extends EntityMapper<CarrinhoDTO, Carrinho> {
}
