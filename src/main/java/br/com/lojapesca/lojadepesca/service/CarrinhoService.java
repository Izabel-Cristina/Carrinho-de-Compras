package br.com.lojapesca.lojadepesca.service;

import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;


public interface CarrinhoService {
    ResponseDTO<CarrinhoDTO> carrinho(CarrinhoDTO carrinhoDTO);
}
