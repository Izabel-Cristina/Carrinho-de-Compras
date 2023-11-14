package br.com.lojapesca.lojadepesca.service.serviceImpl;

import br.com.lojapesca.lojadepesca.bo.ItemBO;
import br.com.lojapesca.lojadepesca.dto.ItemDTO;
import br.com.lojapesca.lojadepesca.dto.ProdutoDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    ItemBO itemBO = new ItemBO();
    @Override
    public  ResponseDTO<ItemDTO> inserirItem(ItemDTO itemDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(itemBO.adicionarItens(itemDTO));
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
    public  ResponseDTO<ItemDTO> removerItem(Long idProduto) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(itemBO.removerItem(idProduto));
        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_REMOVER_ITEM.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_DELETAR_PRODUTO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;

    }@Override
    public  ResponseDTO<ItemDTO>atualizarQuantidadeItem(ItemDTO itemDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(itemBO.atualizarQuantidadeItem(itemDTO));
        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_ATUALIZAR_QUANTIDADE_ITEM.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_ATUALIZAR_QUANTIDADE_ITEM.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }

}
