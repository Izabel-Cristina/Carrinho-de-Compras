package br.com.lojapesca.lojadepesca.dto;

import br.com.lojapesca.lojadepesca.domain.Role;

public record RegistroDTO(String login, String password, Role role) {
}
