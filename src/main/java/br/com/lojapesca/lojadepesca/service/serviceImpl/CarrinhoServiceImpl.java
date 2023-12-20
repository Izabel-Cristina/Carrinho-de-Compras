package br.com.lojapesca.lojadepesca.service.serviceImpl;


import br.com.lojapesca.lojadepesca.bo.CarrinhoBo;
import br.com.lojapesca.lojadepesca.dto.CarrinhoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    CarrinhoBo carrinhoBo;

    @Override
    public ResponseDTO<CarrinhoDTO> carrinho(CarrinhoDTO carrinhoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(carrinhoBo.adicionarProdutoCarrinho(carrinhoDTO));

        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_CARRINHO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_CARRINHO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<CarrinhoDTO> listarCarrinho() {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(carrinhoBo.listarCarrinho());

        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_CARRINHO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_CARRINHO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<CarrinhoDTO>listarCarrinhoId(Long id){
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(carrinhoBo.listarCarrinhoId(id));

        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_CARRINHO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_CARRINHO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }
}
