package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorLoginRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRecuperacionRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CambioContraseniaInicialRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.LoginResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	private final WebClient webClient;

	public AuthServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public LoginResponseDto cambiarContraseniaInicial(Integer idUsuario,
			CambioContraseniaInicialRequestDto request) {
		return webClient.post()
				.uri("/api/usuarios-administradores/{id}/cambiar-contrasenia-inicial", idUsuario)
				.bodyValue(Map.of("nuevaContrasenia", request.getNuevaContrasenia()))
				.retrieve()
				.onStatus(HttpStatusCode::isError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("message",
								"No fue posible cambiar la contraseña"))))
				.bodyToMono(LoginResponseDto.class)
				.block();
	}

	@Override
	public LoginResponseDto login(UsuarioAdministradorLoginRequestDto credenciales) {
		return webClient.post().uri("/api/usuarios-administradores/login").bodyValue(credenciales).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class).map(body ->
						new RuntimeException((String) body.getOrDefault("message", "Credenciales invalidas"))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(LoginResponseDto.class).block();
	}

	@Override
	public String recuperarContrasenia(UsuarioAdministradorRecuperacionRequestDto request) {
		Map<?, ?> response = webClient.post().uri("/api/usuarios-administradores/recuperar-contrasenia")
				.bodyValue(request).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, res -> res.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("message", "Correo no encontrado"))))
				.onStatus(HttpStatusCode::is5xxServerError, res -> res.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("message",
								"No fue posible enviar el correo en este momento"))))
				.bodyToMono(Map.class).block();

		Object message = response != null ? response.get("message") : null;
		return message != null ? message.toString() : "Se envio la contrasenia al correo registrado";
	}
}
