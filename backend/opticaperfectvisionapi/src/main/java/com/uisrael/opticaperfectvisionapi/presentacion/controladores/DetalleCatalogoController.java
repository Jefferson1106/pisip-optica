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

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleCatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleCatalogoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IDetalleCatalogoDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalle-catalogos")
public class DetalleCatalogoController {

	private final IDetalleCatalogoUseCase detalleCatalogoUseCase;
	private final IDetalleCatalogoDtoMapper mapper;

	public DetalleCatalogoController(IDetalleCatalogoUseCase detalleCatalogoUseCase, IDetalleCatalogoDtoMapper mapper) {
		this.detalleCatalogoUseCase = detalleCatalogoUseCase;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<DetalleCatalogoResponseDto>> listarTodos() {
		List<DetalleCatalogoResponseDto> response = detalleCatalogoUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalleCatalogoResponseDto> buscarPorId(@PathVariable int id) {
		DetalleCatalogo detalleCatalogo = detalleCatalogoUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(detalleCatalogo));
	}

	@PostMapping
	public ResponseEntity<DetalleCatalogoResponseDto> guardar(
			@Valid @RequestBody DetalleCatalogoRequestDto requestDto) {
		DetalleCatalogo guardado = detalleCatalogoUseCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DetalleCatalogoResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody DetalleCatalogoRequestDto requestDto) {
		DetalleCatalogo actualizado = detalleCatalogoUseCase.actualizar(id, mapper.toDomain(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@PatchMapping("/{id}/estado")
	public ResponseEntity<DetalleCatalogoResponseDto> actualizarEstado(@PathVariable int id,
			@RequestParam(required = false) Boolean estado,
			@RequestBody(required = false) EstadoPatchRequest request) {
		Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
		if (estadoFinal == null) {
			throw new IllegalArgumentException("El campo estado es obligatorio");
		}
		DetalleCatalogo actualizado = detalleCatalogoUseCase.actualizarEstado(id, estadoFinal);
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
