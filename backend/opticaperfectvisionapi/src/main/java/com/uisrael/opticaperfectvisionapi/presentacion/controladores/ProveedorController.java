package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.List;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ProveedorEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IProveedorJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.ProveedorRequestDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
	private final IProveedorJpaRepositorio repositorio;

	public ProveedorController(IProveedorJpaRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@GetMapping
	public List<ProveedorEntity> listar() { return repositorio.findAll(); }

	@GetMapping("/{id}")
	public ProveedorEntity buscar(@PathVariable Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
	}

	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody ProveedorRequestDto dto) {
		if (repositorio.existsByNombreIgnoreCase(dto.getNombre().trim()))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un proveedor con ese nombre"));
		if (repositorio.existsByIdentificacion(dto.getIdentificacion()))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un proveedor con esa identificación"));
		ProveedorEntity entity = aplicar(new ProveedorEntity(), dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(entity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody ProveedorRequestDto dto) {
		if (repositorio.existsByNombreIgnoreCaseAndIdProveedorNot(dto.getNombre().trim(), id))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un proveedor con ese nombre"));
		if (repositorio.existsByIdentificacionAndIdProveedorNot(dto.getIdentificacion(), id))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un proveedor con esa identificación"));
		ProveedorEntity entity = buscar(id);
		return ResponseEntity.ok(repositorio.save(aplicar(entity, dto)));
	}

	private ProveedorEntity aplicar(ProveedorEntity entity, ProveedorRequestDto dto) {
		entity.setNombre(dto.getNombre().trim());
		entity.setIdentificacion(dto.getIdentificacion());
		entity.setCorreo(dto.getCorreo());
		entity.setTelefono(dto.getTelefono());
		entity.setDireccion(dto.getDireccion());
		entity.setEstado(dto.isEstado());
		return entity;
	}
}
