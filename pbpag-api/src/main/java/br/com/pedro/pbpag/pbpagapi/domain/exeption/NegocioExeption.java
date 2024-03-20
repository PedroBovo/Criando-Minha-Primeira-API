package br.com.pedro.pbpag.pbpagapi.domain.exeption;

public class NegocioExeption extends RuntimeException{
    public NegocioExeption(String message) {
        super(message);
    }
}
