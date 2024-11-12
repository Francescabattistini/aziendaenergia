package team5.azienda.energia.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String msg) {
		super(msg);
	}
}
