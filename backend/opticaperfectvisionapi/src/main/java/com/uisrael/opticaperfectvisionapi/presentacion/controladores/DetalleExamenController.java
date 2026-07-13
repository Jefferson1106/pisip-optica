package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleExamenUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleExamenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleExamenResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IDetalleExamenDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalle-examen")
public class DetalleExamenController {
	
	private final IDetalleExamenUseCase detalleExamenUseCase;
	
	private final IDetalleExamenDtoMapper mapper;

	public DetalleExamenController(IDetalleExamenUseCase detalleExamenUseCase, IDetalleExamenDtoMapper mapper) {
		this.detalleExamenUseCase = detalleExamenUseCase;
		this.mapper = mapper;
	}
	
	@GetMapping
	public ResponseEntity<List<DetalleExamenResponseDto>> listarTodos(){
		
		List<DetalleExamenResponseDto> response = detalleExamenUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalleExamenResponseDto> buscarPorId(@PathVariable int id) {
		
		DetalleExamen detalleExamen = detalleExamenUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(detalleExamen));
		
	}
	
	@PostMapping
	public ResponseEntity<DetalleExamenResponseDto> guardar(@Valid @RequestBody DetalleExamenRequestDto requestDto){
		DetalleExamen detalleExamenGuardado = detalleExamenUseCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(detalleExamenGuardado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetalleExamenResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody DetalleExamenRequestDto requestDto){
		DetalleExamen actualizado = detalleExamenUseCase.actualizar(id, mapper.toDomain(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}
	


}
