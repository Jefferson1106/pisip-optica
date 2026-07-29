package com.uisrael.opticaperfectvisionapi.presentacion.controladores;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.*;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.*;
import com.uisrael.opticaperfectvisionapi.presentacion.dto.request.ProductoRequestDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	private final IProductoJpaRepositorio repositorio;
	private final IProveedorJpaRepositorio proveedorRepositorio;

	public ProductoController(IProductoJpaRepositorio repositorio, IProveedorJpaRepositorio proveedorRepositorio) {
		this.repositorio = repositorio;
		this.proveedorRepositorio = proveedorRepositorio;
	}

	@GetMapping
	public List<Map<String, Object>> listar() { return repositorio.findAll().stream().map(this::respuesta).toList(); }

	@GetMapping("/{id}")
	public Map<String, Object> buscar(@PathVariable Integer id) { return respuesta(buscarEntity(id)); }

	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody ProductoRequestDto dto) {
		if (repositorio.existsByCodigoIgnoreCase(dto.getCodigo().trim()))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un producto con ese código"));
		ProductoEntity entity = aplicar(new ProductoEntity(), dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta(repositorio.save(entity)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDto dto) {
		if (repositorio.existsByCodigoIgnoreCaseAndIdProductoNot(dto.getCodigo().trim(), id))
			return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un producto con ese código"));
		return ResponseEntity.ok(respuesta(repositorio.save(aplicar(buscarEntity(id), dto))));
	}

	private ProductoEntity buscarEntity(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	}

	private ProductoEntity aplicar(ProductoEntity entity, ProductoRequestDto dto) {
		ProveedorEntity proveedor = proveedorRepositorio.findById(dto.getIdProveedor())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
		entity.setCodigo(dto.getCodigo().trim());
		entity.setNombre(dto.getNombre().trim());
		entity.setDescripcion(dto.getDescripcion());
		entity.setPrecio(dto.getPrecio());
		entity.setProveedor(proveedor);
		entity.setEstado(dto.isEstado());
		return entity;
	}

	private Map<String, Object> respuesta(ProductoEntity entity) {
		Map<String, Object> dto = new LinkedHashMap<>();
		dto.put("idProducto", entity.getIdProducto());
		dto.put("codigo", entity.getCodigo());
		dto.put("nombre", entity.getNombre());
		dto.put("descripcion", entity.getDescripcion());
		dto.put("precio", entity.getPrecio());
		dto.put("idProveedor", entity.getProveedor().getIdProveedor());
		dto.put("proveedorNombre", entity.getProveedor().getNombre());
		dto.put("estado", entity.isEstado());
		return dto;
	}
}
