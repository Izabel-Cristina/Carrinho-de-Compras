package br.com.lojapesca.lojadepesca.bo;

public class ExceptionsBo extends Exception{
    public ExceptionsBo() {
        super();
    }

    public ExceptionsBo(String message) {
        super(message);
    }

    public ExceptionsBo(String message, Throwable cause) {
        super(message, cause);
    }
}
