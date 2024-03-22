package br.com.pedro.pbpag.pbpagapi.controler.exceptionhandler;

import br.com.pedro.pbpag.pbpagapi.domain.exeption.NegocioExeption;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExcepitonHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estao invalidos");
        problemDetail.setType(URI.create("https://pedrobovo.com/erros/campos-invalidos"));

       var fields =  ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error -> ((FieldError)error).getField(),
                       error -> messageSource.getMessage(error, LocaleContextHolder.getLocale()) ));

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail,headers, status, request);
    }

    @ExceptionHandler(NegocioExeption.class)
    public ProblemDetail hendleNegocio(NegocioExeption e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://pedrobovo.com/erros/regra-de-negocio"));

        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso esta em uso");
        problemDetail.setType(URI.create("https://pedrobovo.com/erros/recurso-em-uso"));

        return problemDetail;
    }
}
