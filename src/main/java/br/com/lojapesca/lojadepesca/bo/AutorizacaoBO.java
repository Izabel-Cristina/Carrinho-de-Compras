package br.com.lojapesca.lojadepesca.bo;

import br.com.lojapesca.lojadepesca.domain.Usuario;
import br.com.lojapesca.lojadepesca.dto.AutenticacaoDTO;
import br.com.lojapesca.lojadepesca.dto.LoginResponseDTO;
import br.com.lojapesca.lojadepesca.dto.RegistroDTO;
import br.com.lojapesca.lojadepesca.repository.UsuarioRepository;
import br.com.lojapesca.lojadepesca.security.TokenService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AutorizacaoBO{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public LoginResponseDTO login(AutenticacaoDTO data) {

        var useLogineSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(useLogineSenha);

        var token = tokenService.geradorToken((Usuario) auth.getPrincipal());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token);


        return loginResponseDTO;

    }

    public String registro(RegistroDTO registroDTO) throws Exception {

        if (this.usuarioRepository.findByLogin(registroDTO.login()) != null) {
            throw new Exception();
        } else {
            String encryptoSenha = new BCryptPasswordEncoder().encode(registroDTO.password());
            Usuario usuario = new Usuario(registroDTO.login(), encryptoSenha, registroDTO.role());
            this.usuarioRepository.save(usuario);
        }
        return ("Usuário registrado com sucesso!");
    }
}
