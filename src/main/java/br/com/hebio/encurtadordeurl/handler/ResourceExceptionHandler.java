package br.com.hebio.encurtadordeurl.handler;

import br.com.hebio.encurtadordeurl.model.ErrorDetails;
import br.com.hebio.encurtadordeurl.service.exceptions.LinkNotFoundException;
import br.com.hebio.encurtadordeurl.service.exceptions.URLAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleLinkNotFoundException(LinkNotFoundException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(404l);
        errorDetails.setTitle("O link não pôde ser encontrado");
        errorDetails.setDeveloperMessage("http://erros.encurtador-de-url.com.br/404");
        errorDetails.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(URLAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleURLAlreadyExistsException(URLAlreadyExistsException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(409l);
        errorDetails.setTitle("Já existe um redirecionamento para a URL inserida");
        errorDetails.setDeveloperMessage("http://erros.encurtador-de-url.com.br/409");
        errorDetails.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
