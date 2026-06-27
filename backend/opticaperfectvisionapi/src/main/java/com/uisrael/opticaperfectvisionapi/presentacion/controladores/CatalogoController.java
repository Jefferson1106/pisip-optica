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

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.ICatalogoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.CatalogoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.CatalogoResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.ICatalogoDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {
	private final ICatalogoUseCase catalogoUseCase;
	private final ICatalogoDtoMapper mapper;

	public CatalogoController(ICatalogoUseCase catalogoUseCase, ICatalogoDtoMapper mapper) {
		this.catalogoUseCase = catalogoUseCase;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<CatalogoResponseDto>> listarTodos() {
		List<CatalogoResponseDto> response = catalogoUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CatalogoResponseDto> buscarPorId(@PathVariable int id) {
		Catalogo catalogo = catalogoUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(catalogo));
	}

	@PostMapping
	public ResponseEntity<CatalogoResponseDto> guardar(@Valid @RequestBody CatalogoRequestDto requestDto) {
		Catalogo catalogoGuardado = catalogoUseCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(catalogoGuardado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CatalogoResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody CatalogoRequestDto requestDto) {
		Catalogo catalogoActualizado = catalogoUseCase.actualizar(id, mapper.toDomain(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(catalogoActualizado));
	}

	@PatchMapping("/{id}/estado")
	public ResponseEntity<CatalogoResponseDto> actualizarEstado(@PathVariable int id,
			@RequestParam(required = false) Boolean estado,
			@RequestBody(required = false) EstadoPatchRequest request) {
		Boolean estadoFinal = estado != null ? estado : (request != null ? request.getEstado() : null);
		if (estadoFinal == null) {
			throw new IllegalArgumentException("El campo estado es obligatorio");
		}
		Catalogo catalogoActualizado = catalogoUseCase.actualizarEstado(id, estadoFinal);
		return ResponseEntity.ok(mapper.toResponseDto(catalogoActualizado));
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
