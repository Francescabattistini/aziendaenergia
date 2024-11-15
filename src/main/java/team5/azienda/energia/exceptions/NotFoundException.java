package team5.azienda.energia.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(int id) {
		super("Il record con id " + id + " non è stato trovato!");
	}
	public NotFoundException(long id) {
		super("Il record con id " + id + " non è stato trovato!");
	}
	public NotFoundException(String mes) {
		super(mes);
	}
}
