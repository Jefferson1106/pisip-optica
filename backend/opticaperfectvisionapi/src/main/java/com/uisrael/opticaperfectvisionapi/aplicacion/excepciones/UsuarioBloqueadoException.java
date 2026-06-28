package com.uisrael.opticaperfectvisionapi.aplicacion.excepciones;

public class UsuarioBloqueadoException extends RuntimeException {

	public UsuarioBloqueadoException(String message) {
		super(message);
	}
}