package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleOrdenUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleOrden;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.DetalleOrdenRequestDto;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.response.DetalleOrdenResponseDto;
import com.uisrael.opticaperfectvisionapi.presentacion.mapeadores.IDetalleOrdenDtoMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ProductoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IProductoJpaRepositorio;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/detalle-orden")
public class DetalleOrdenController {
	
	private final IDetalleOrdenUseCase detalleOrdenUseCase;
	private final IDetalleOrdenDtoMapper mapper;
	private final IProductoJpaRepositorio productoRepositorio;
	
	public DetalleOrdenController(IDetalleOrdenUseCase detalleOrdenUseCase, IDetalleOrdenDtoMapper mapper,
			IProductoJpaRepositorio productoRepositorio) {
		this.detalleOrdenUseCase = detalleOrdenUseCase;
		this.mapper = mapper;
		this.productoRepositorio = productoRepositorio;
	}
	
	@GetMapping
	public ResponseEntity<List<DetalleOrdenResponseDto>> listarTodos(){
		List<DetalleOrdenResponseDto> response = detalleOrdenUseCase.listarTodos().stream()
				.map(mapper::toResponseDto)
				.toList();
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalleOrdenResponseDto> buscarPorId(@PathVariable int id){
		DetalleOrden detalleOrden = detalleOrdenUseCase.buscarPorId(id);
		return ResponseEntity.ok(mapper.toResponseDto(detalleOrden));
	}
	
	@PostMapping
	public ResponseEntity<DetalleOrdenResponseDto> guardar(
			@Valid @RequestBody DetalleOrdenRequestDto requestDto){
		DetalleOrden guardado = detalleOrdenUseCase.guardar(prepararDetalle(requestDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(guardado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetalleOrdenResponseDto> actualizar(@PathVariable int id,
			@Valid @RequestBody DetalleOrdenRequestDto requestDto){
		DetalleOrden actualizado = detalleOrdenUseCase.actualizar(id, prepararDetalle(requestDto));
		return ResponseEntity.ok(mapper.toResponseDto(actualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable int id) {
		detalleOrdenUseCase.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	private DetalleOrden prepararDetalle(DetalleOrdenRequestDto requestDto) {
		ProductoEntity producto = productoRepositorio.findById(requestDto.getIdProducto())
				.filter(ProductoEntity::isEstado)
				.orElseThrow(() -> new IllegalArgumentException("Producto activo no encontrado"));
		DetalleOrden detalle = mapper.toDomain(requestDto);
		detalle.setProducto(producto);
		detalle.setPrecioUnitario(producto.getPrecio());
		return detalle;
	}

}
