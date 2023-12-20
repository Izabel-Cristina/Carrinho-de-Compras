package br.com.lojapesca.lojadepesca.service;

import br.com.lojapesca.lojadepesca.dto.AutenticacaoDTO;
import br.com.lojapesca.lojadepesca.dto.LoginResponseDTO;
import br.com.lojapesca.lojadepesca.dto.RegistroDTO;
import br.com.lojapesca.lojadepesca.dto.ResponseDTO;

public interface AuthorizationService {
    ResponseDTO<LoginResponseDTO> login(AutenticacaoDTO data) ;
    ResponseDTO<String> registro(RegistroDTO registroDTO);

}
