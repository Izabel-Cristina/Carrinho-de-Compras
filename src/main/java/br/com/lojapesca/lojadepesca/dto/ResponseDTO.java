package br.com.lojapesca.lojadepesca.dto;

import lombok.Data;

import java.util.*;

@Data
public class ResponseDTO<T> {

    private Integer code;
    private Status status;
    private String errorMessage;
    private Object data;

    public ResponseDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum Status {
        SUCCESS, ERROR, WARN;
    }

    public enum Code {
        SUCCESS(0, "Operação realizada com sucesso"),
        ERROR_PRODUTO(1, "Erro ao inserir produto"),
        ERROR_PRODUTO_INVALIDO(2,"Produto não encontrado."),
        ERROR_CARRINHO(3,"Verifique os itens no seu carrinho");

        private Integer code;
        private String messageCode;

        private static Map<Integer, Code> codeMap = new HashMap<>();

        static {
            for (Code code : Code.values()) {
                codeMap.put(code.code, code);
            }
        }

        Code(Integer code, String messageCode) {
            this.code = code;
            this.messageCode = messageCode;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessageCode() {
            return messageCode;
        }

        public static Code findByCode(Integer code) {
            return codeMap.get(code);
        }

    }
}






