package br.com.hebio.encurtadordeurl.service.exceptions;

public class URLAlreadyExistsException extends RuntimeException {
    public URLAlreadyExistsException(String message) {
        super(message);
    }

    public URLAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
