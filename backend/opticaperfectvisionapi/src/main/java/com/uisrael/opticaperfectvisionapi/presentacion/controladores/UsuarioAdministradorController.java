package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IUsuarioAdministradorUseCase;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.CredencialesInvalidasException;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.UsuarioBloqueadoException;
import com.uisrael.opticaperfectvisionapi.aplicacion.excepciones.UsuarioNoEncontradoException;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorLoginRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorRecuperacionRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.UsuarioAdministradorUpdateRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.LoginResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.UsuarioAdministradorResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IUsuarioAdministradorDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios-administradores")
public class UsuarioAdministradorController {

	private final IUsuarioAdministradorUseCase useCase;
	private final IUsuarioAdministradorDtoMapper mapper;

	public UsuarioAdministradorController(IUsuarioAdministradorUseCase useCase,
			IUsuarioAdministradorDtoMapper mapper) {
		this.useCase = useCase;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioAdministradorResponseDto>> listarTodos() {
		List<UsuarioAdministradorResponseDto> response = useCase.listarTodos().stream().map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAdministradorResponseDto> buscarPorId(@PathVariable Integer id) {
		UsuarioAdministrador usuario = useCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(usuario));
	}

	@PostMapping
	public ResponseEntity<UsuarioAdministradorResponseDto> guardar(
			@Valid @RequestBody UsuarioAdministradorRequestDto requestDto) {
		UsuarioAdministrador guardado = useCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioAdministradorResponseDto> actualizar(@PathVariable Integer id,
			@Valid @RequestBody UsuarioAdministradorUpdateRequestDto requestDto) {
		UsuarioAdministrador existente = useCase.buscarPorId(id);

		if (requestDto.getIdTipoUsuario() != null) {
			DetalleCatalogoEntity tipoUsuario = new DetalleCatalogoEntity();
			tipoUsuario.setIdDetalleCatalogo(requestDto.getIdTipoUsuario());
			existente.setTipoUsuario(tipoUsuario);
		}
		if (requestDto.getNombres() != null) {
			existente.setNombres(requestDto.getNombres());
		}
		if (requestDto.getApellidos() != null) {
			existente.setApellidos(requestDto.getApellidos());
		}
		if (requestDto.getCorreo() != null) {
			existente.setCorreo(requestDto.getCorreo());
		}
		if (requestDto.getContrasenia() != null) {
			existente.setContrasenia(requestDto.getContrasenia());
		}
		if (requestDto.getEstado() != null) {
			existente.setEstado(requestDto.getEstado());
		}

		UsuarioAdministrador actualizado = useCase.actualizar(id, existente);
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@PatchMapping("/{id}/estado")
	public ResponseEntity<UsuarioAdministradorResponseDto> actualizarEstado(@PathVariable Integer id,
			@RequestParam(required = false) Boolean estado,
			@RequestBody(required = false) EstadoPatchRequest request) {
		Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
		if (estadoFinal == null) {
			throw new IllegalArgumentException("El campo estado es obligatorio");
		}
		UsuarioAdministrador actualizado = useCase.actualizarEstado(id, estadoFinal);
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UsuarioAdministradorLoginRequestDto requestDto) {
		try {
			UsuarioAdministrador usuario = useCase.login(requestDto.getCorreo(), requestDto.getContrasenia());
			LoginResponseDto response = mapper.toLoginResponseDto(usuario);
			return ResponseEntity.ok(response);
		} catch (UsuarioBloqueadoException e) {
			return ResponseEntity.status(HttpStatus.LOCKED).body(e.getMessage());
		} catch (CredencialesInvalidasException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/recuperar-contrasenia")
	public ResponseEntity<String> recuperarContrasenia(
			@Valid @RequestBody UsuarioAdministradorRecuperacionRequestDto requestDto) {
		try {
			useCase.recuperarContrasenia(requestDto.getCorreo());
			return ResponseEntity.ok("Se envio la contrasenia al correo registrado");
		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	private static class EstadoPatchRequest {
		private Boolean estado;

		public Boolean getEstado() {
			return estado;
		}

		public void setEstado(Boolean estado) {
			this.estado = estado;
		}
	}
}