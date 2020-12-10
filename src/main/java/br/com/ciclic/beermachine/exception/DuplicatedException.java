package br.com.ciclic.beermachine.exception;

public class DuplicatedException extends Exception {

    public DuplicatedException(String message) {
        super(message);
    }
}