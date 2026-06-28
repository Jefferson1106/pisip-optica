package com.uisrael.opticaperfectvisionapi.infraestructura.servicios;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.uisrael.opticaperfectvisionapi.aplicacion.servicios.IEnvioCorreoService;

@Service
public class EnvioCorreoServiceImpl implements IEnvioCorreoService {

	private final JavaMailSender mailSender;

	public EnvioCorreoServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void enviarContrasenia(String destinatario, String contrasenia) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(destinatario);
		mensaje.setSubject("Recuperacion de usuario administrador");
		mensaje.setText("Su contrasenia registrada es: " + contrasenia);
		mailSender.send(mensaje);
	}
}