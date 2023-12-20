package br.com.lojapesca.lojadepesca.service.serviceImpl;

import br.com.lojapesca.lojadepesca.bo.AutorizacaoBO;
import br.com.lojapesca.lojadepesca.dto.AutenticacaoDTO;
import br.com.lojapesca.lojadepesca.dto.LoginResponseDTO;
import br.com.lojapesca.lojadepesca.dto.RegistroDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;
import br.com.lojapesca.lojadepesca.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImp implements AuthorizationService {

    @Autowired
    AutorizacaoBO autorizacaoBO;


    @Override
    public ResponseDTO<LoginResponseDTO> login(AutenticacaoDTO data) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(autorizacaoBO.login(data));
        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_LOGIN.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_LOGIN.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }



    @Override
    public ResponseDTO<String> registro(RegistroDTO registroDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            responseDTO.setCode(ResponseDTO.Status.SUCCESS.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.SUCCESS);
            responseDTO.setErrorMessage(ResponseDTO.Code.SUCCESS.getMessageCode());
            responseDTO.setData(autorizacaoBO.registro(registroDTO));
        } catch (Exception e) {
            responseDTO.setCode(ResponseDTO.Status.ERROR.ordinal());
            responseDTO.setStatus(ResponseDTO.Status.ERROR);
            responseDTO.setCode(ResponseDTO.Code.ERROR_REGISTRO.getCode());
            responseDTO.setErrorMessage(ResponseDTO.Code.ERROR_REGISTRO.getMessageCode());
            responseDTO.setData(null);
        }
        return responseDTO;
    }
}
