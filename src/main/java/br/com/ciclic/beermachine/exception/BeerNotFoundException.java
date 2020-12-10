package br.com.ciclic.beermachine.exception;

public class BeerNotFoundException extends Exception {

    public BeerNotFoundException() {
        super();
    }

    public BeerNotFoundException(String message) {
        super(message);
    }
}