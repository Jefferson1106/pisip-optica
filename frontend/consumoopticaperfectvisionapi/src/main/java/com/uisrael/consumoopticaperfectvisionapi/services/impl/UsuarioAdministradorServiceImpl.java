package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorUpdateRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.UsuarioAdministradorResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IUsuarioAdministradorService;

@Service
public class UsuarioAdministradorServiceImpl implements IUsuarioAdministradorService {

	private final WebClient webClient;

	public UsuarioAdministradorServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public List<UsuarioAdministradorResponseDto> listarUsuarios() {
		return webClient.get().uri("/api/usuarios-administradores").retrieve()
				.bodyToFlux(UsuarioAdministradorResponseDto.class).collectList().block();
	}

	@Override
	public UsuarioAdministradorResponseDto buscarPorId(Integer idUsuario) {
		return webClient.get().uri("/api/usuarios-administradores/" + idUsuario).retrieve()
				.bodyToMono(UsuarioAdministradorResponseDto.class).block();
	}

	@Override
	public void guardarUsuario(UsuarioAdministradorRequestDto usuario) {
		webClient.post().uri("/api/usuarios-administradores").bodyValue(usuario).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("message",
								(String) body.getOrDefault("error", "Validación inválida")))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(UsuarioAdministradorResponseDto.class).block();
	}

	@Override
	public void actualizarUsuario(Integer idUsuario, UsuarioAdministradorUpdateRequestDto usuario) {
		webClient.put().uri("/api/usuarios-administradores/" + idUsuario).bodyValue(usuario).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(Map.class)
						.map(body -> new RuntimeException((String) body.getOrDefault("message",
								(String) body.getOrDefault("error", "Validación inválida")))))
				.onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
						.map(msg -> new RuntimeException("Error del servidor: " + msg)))
				.bodyToMono(UsuarioAdministradorResponseDto.class).block();
	}
}