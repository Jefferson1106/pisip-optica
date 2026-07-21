package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IOrdenPedidoUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.OrdenPedidoRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.OrdenPedidoResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IOrdenPedidoDtoMapper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/detalle-ordenPedido")
public class OrdenPedidoController {
	
	private final IOrdenPedidoUseCase ordenPedidoUseCase;
	private final IOrdenPedidoDtoMapper mapper;
	
	public OrdenPedidoController(IOrdenPedidoUseCase ordenPedidoUseCase, IOrdenPedidoDtoMapper mapper) {
		this.ordenPedidoUseCase = ordenPedidoUseCase;
		this.mapper = mapper;
	}
	
	@GetMapping
	public ResponseEntity<List<OrdenPedidoResponseDto>> listarTodos(){
		List<OrdenPedidoResponseDto> response = ordenPedidoUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdenPedidoResponseDto> buscarPorId(@PathVariable int id){
		OrdenPedido ordenPedido = ordenPedidoUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(ordenPedido));
	}
	
	@PostMapping
	public ResponseEntity<OrdenPedidoResponseDto> guardar(
			@Valid @RequestBody OrdenPedidoRequestDto requestDto){
		OrdenPedido guardado = ordenPedidoUseCase.guardar(mapper.toDomain(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrdenPedidoResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody OrdenPedidoRequestDto requestDto){
		OrdenPedido actualizado = ordenPedidoUseCase.actualizar(id, mapper.toDomain(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) {
		try {
			ordenPedidoUseCase.eliminar(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
		}
	}
	

}
