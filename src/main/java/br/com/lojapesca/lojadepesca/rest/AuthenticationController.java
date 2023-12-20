package br.com.lojapesca.lojadepesca.rest;

import br.com.lojapesca.lojadepesca.domain.Usuario;
import br.com.lojapesca.lojadepesca.dto.AutenticacaoDTO;
import br.com.lojapesca.lojadepesca.dto.LoginResponseDTO;
import br.com.lojapesca.lojadepesca.dto.RegistroDTO;
import br.com.lojapesca.lojadepesca.repository.UsuarioRepository;
import br.com.lojapesca.lojadepesca.security.TokenService;
import br.com.lojapesca.lojadepesca.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseResource{

    @Autowired
    private AuthorizationService authorizationService;

    @Operation(summary = "Realizar login", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AutenticacaoDTO data){
        return(ResponseEntity<LoginResponseDTO>)createdCodeReturn(authorizationService.login(data));
    }

    @Operation(summary = "Realizar o registro de login", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Falha na operação")})
    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody @Valid RegistroDTO registroDTO){
        return (ResponseEntity<String>) createdCodeReturn(authorizationService.registro(registroDTO));
//
    }
}
