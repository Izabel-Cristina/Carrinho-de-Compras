package br.com.lojapesca.lojadepesca.bo;


import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CarrinhoBo {

    public CarrinhoDTO carrinho(CarrinhoDTO carrinhoDTO) throws Exception {
        carrinhoDTO.getProduto();


        Integer quantidade = carrinhoDTO.getQuantidadeProduto();
        Double precoUnitario = carrinhoDTO.getProduto().getPreco();


        Double precoTotal = +quantidade * precoUnitario;
        carrinhoDTO.setPrecoTotalProduto(precoTotal);

        String mensagem = "";

        try {
            carrinhoDTO.getProduto();
            carrinhoDTO.getPrecoTotalProduto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carrinhoDTO;
    }
}
