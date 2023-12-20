package br.com.lojapesca.lojadepesca.security;

import br.com.lojapesca.lojadepesca.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();

        // Lista de rotas públicas (ajuste conforme suas rotas permitAll)
        List<String> rotasPublicas = Arrays.asList("/api/v1/carrinhos/carrinho", "/api/v1/produtos/produtos","/swagger-ui/index.html#");

        // Verifica se a rota atual é pública (não requer autenticação)
        if (rotasPublicas.contains(requestUri)) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validacaoToken(token);
            UserDetails user = usuarioRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
