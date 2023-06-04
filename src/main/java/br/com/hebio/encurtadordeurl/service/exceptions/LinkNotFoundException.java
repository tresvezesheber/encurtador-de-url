package br.com.hebio.encurtadordeurl.service.exceptions;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String message) {
        super(message);
    }

    public LinkNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
