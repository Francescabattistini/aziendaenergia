package team5.azienda.energia.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(Long id) {
		super("Il record con id " + id + " non è stato trovato!");
	}
}
