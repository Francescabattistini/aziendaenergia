package team5.azienda.energia.exceptions;

public class UnathorizLoginExeption extends RuntimeException {
    public UnathorizLoginExeption(String message) {
        super(message);
    }
}
