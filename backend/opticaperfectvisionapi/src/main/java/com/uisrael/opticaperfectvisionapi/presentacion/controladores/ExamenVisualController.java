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

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IExamenVisualUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.ExamenVisual;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.ExamenVisualRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.ExamenVisualResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IExamenVisualDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/examenes-visuales")
public class ExamenVisualController {

	private final IExamenVisualUseCase examenVisualUseCase;
	private final IExamenVisualDtoMapper mapper;

	public ExamenVisualController(IExamenVisualUseCase examenVisualUseCase, IExamenVisualDtoMapper mapper) {
		this.examenVisualUseCase = examenVisualUseCase;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<ExamenVisualResponseDto>> listarTodos() {
		List<ExamenVisualResponseDto> response = examenVisualUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExamenVisualResponseDto> buscarPorId(@PathVariable int id) {
		ExamenVisual examenVisual = examenVisualUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(examenVisual));
	}

	@PostMapping
	public ResponseEntity<ExamenVisualResponseDto> guardar(@Valid @RequestBody ExamenVisualRequestDto requestDto) {
		ExamenVisual guardado = examenVisualUseCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExamenVisualResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody ExamenVisualRequestDto requestDto) {
		ExamenVisual actualizado = examenVisualUseCase.actualizar(id, mapper.toDomain(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@PatchMapping("/{id}/estado")
	public ResponseEntity<ExamenVisualResponseDto> actualizarEstado(@PathVariable int id,
			@RequestParam(required = false) Boolean estado,
			@RequestBody(required = false) EstadoPatchRequest request) {
		Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
		if (estadoFinal == null) {
			throw new IllegalArgumentException("El campo estado es obligatorio");
		}
		ExamenVisual actualizado = examenVisualUseCase.actualizarEstado(id, estadoFinal);
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
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
