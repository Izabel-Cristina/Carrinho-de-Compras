package br.com.lojapesca.lojadepesca.service.serviceImpl;

import br.com.lojapesca.lojadepesca.bo.ProdutoBo;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ProdutoService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    ProdutoBo produtoBo = new ProdutoBo();

    @Override
    public ResponseDTO<ProdutoDTO> inserirProduto(ProdutoDTO produtoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(produtoBo.inserirProduto(produtoDTO));
        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_PRODUTO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_PRODUTO.getMessageCode());
            responseDTO.setData(null);

        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<ProdutoDTO> listarProdutos() {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(produtoBo.listarProdutos());

        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_PRODUTO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_PRODUTO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<ProdutoDTO> obterProdutosNome(String nome) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(produtoBo.obterProdutosNome(nome));

        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_PRODUTO_INVALIDO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_PRODUTO_INVALIDO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }
}
