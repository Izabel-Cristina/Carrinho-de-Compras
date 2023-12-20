package br.com.lojapesca.lojadepesca.domain;

import lombok.*;


@Getter
@NoArgsConstructor
public enum Role {
    USER("user"), ADMIN("admin");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRule(){
        return role;
    }
    public static Role toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

//        for(Role x : Role.values()) {
//            if(cod.equals(x.getCodigo())) {
//                return x;
//            }
//        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}
