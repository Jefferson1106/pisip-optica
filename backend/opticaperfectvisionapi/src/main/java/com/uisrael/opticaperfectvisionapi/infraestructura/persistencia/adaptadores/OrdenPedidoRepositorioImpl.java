package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.OrdenPedido;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleOrdenEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.ExamenVisualEntity;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IOrdenPedidoRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenPedidoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.OrdenEntregaEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IOrdenPedidoJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleOrdenJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IExamenVisualJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenEntregaJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IOrdenPedidoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IPacienteJpaRepositorio;

public class OrdenPedidoRepositorioImpl implements IOrdenPedidoRepositorio {
	
	private final IOrdenPedidoJpaRepositorio jpaRepositorio;
	private final IOrdenPedidoJpaMapper entityMapper;
	private final IExamenVisualJpaRepositorio examenVisualJpaRepositorio;
	private final IPacienteJpaRepositorio pacienteJpaRepositorio;
	private final IDetalleCatalogoJpaRepositorio detalleCatalogoJpaRepositorio;
	private final IDetalleOrdenJpaRepositorio detalleOrdenJpaRepositorio;
	private final IOrdenEntregaJpaRepositorio ordenEntregaJpaRepositorio;
	public OrdenPedidoRepositorioImpl(IOrdenPedidoJpaRepositorio jpaRepositorio,
			IOrdenPedidoJpaMapper entityMapper,
			IExamenVisualJpaRepositorio examenVisualJpaRepositorio,
			IPacienteJpaRepositorio pacienteJpaRepositorio,
			IDetalleCatalogoJpaRepositorio detalleCatalogoJpaRepositorio,
			IDetalleOrdenJpaRepositorio detalleOrdenJpaRepositorio,
			IOrdenEntregaJpaRepositorio ordenEntregaJpaRepositorio) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
		this.examenVisualJpaRepositorio = examenVisualJpaRepositorio;
		this.pacienteJpaRepositorio = pacienteJpaRepositorio;
		this.detalleCatalogoJpaRepositorio = detalleCatalogoJpaRepositorio;
		this.detalleOrdenJpaRepositorio = detalleOrdenJpaRepositorio;
		this.ordenEntregaJpaRepositorio = ordenEntregaJpaRepositorio;
	}
	@Override
	public OrdenPedido guardar(OrdenPedido nuevaOrdenPedido) {
		OrdenPedidoEntity entity=entityMapper.toEntity(nuevaOrdenPedido);
		entity.setExamenVisual(obtenerExamenAdministrado(entity.getExamenVisual()));
		entity.setPaciente(obtenerPacienteAdministrado(entity.getPaciente()));
		entity.setEstadoPedido(obtenerEstadoPedidoAdministrado(entity.getEstadoPedido()));
		OrdenPedidoEntity guardado=jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}
	@Override
	public Optional<OrdenPedido> buscarPorId(int idOrdenPedido) {
		
		return jpaRepositorio.findByIdWithRelaciones(idOrdenPedido).map(entityMapper :: toDomain);
	}
	@Override
	public List<OrdenPedido> listarTodos() {
		
		return jpaRepositorio.findAllWithRelaciones().stream().map(entityMapper :: toDomain).toList();
	}
	@Override
	public OrdenPedido actualizar(int idOrdenPedido, OrdenPedido ordenPedido) {
		
		OrdenPedidoEntity existente = jpaRepositorio.findByIdWithRelaciones(idOrdenPedido).orElseThrow(() -> new RuntimeException("Orden pedido no encontrado"));
		
		existente.setExamenVisual(obtenerExamenAdministrado(ordenPedido.getExamenVisual()));
		existente.setPaciente(obtenerPacienteAdministrado(ordenPedido.getPaciente()));
		existente.setFechaPedido(ordenPedido.getFechaPedido());
		existente.setFechaEntrega(ordenPedido.getFechaEntrega());
		existente.setEstadoPedido(obtenerEstadoPedidoAdministrado(ordenPedido.getEstadoPedido()));
		existente.setFechaRegistro(ordenPedido.getFechaRegistro());
		
		OrdenPedidoEntity guardado = jpaRepositorio.save(existente);
		
		return entityMapper.toDomain(guardado);
	}

	@Override
	public void eliminar(int idOrdenPedido) {
		OrdenPedidoEntity existente = jpaRepositorio.findByIdWithRelaciones(idOrdenPedido)
				.orElseThrow(() -> new RuntimeException("Orden pedido no encontrado"));

		long detallesAsociados = detalleOrdenJpaRepositorio.countByOrdenPedido_IdPedido(idOrdenPedido);
		long entregasAsociadas = ordenEntregaJpaRepositorio.countByOrdenPedido_IdPedido(idOrdenPedido);
		if (detallesAsociados > 0 || entregasAsociadas > 0) {
			throw new IllegalStateException("No se puede eliminar la orden porque ya tiene detalles o entregas asociadas");
		}
		jpaRepositorio.delete(existente);
	}

	private ExamenVisualEntity obtenerExamenAdministrado(ExamenVisualEntity examenVisual) {
		if (examenVisual == null || examenVisual.getIdExamen() == null) {
			throw new RuntimeException("El id del examen es obligatorio");
		}

		return examenVisualJpaRepositorio.findById(examenVisual.getIdExamen())
				.orElseThrow(() -> new RuntimeException("Examen visual no encontrado"));
	}

	private PacienteEntity obtenerPacienteAdministrado(PacienteEntity paciente) {
		if (paciente == null || paciente.getIdPaciente() == null) {
			throw new RuntimeException("El id del paciente es obligatorio");
		}

		return pacienteJpaRepositorio.findById(paciente.getIdPaciente())
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
	}

	private DetalleCatalogoEntity obtenerEstadoPedidoAdministrado(DetalleCatalogoEntity estadoPedido) {
		if (estadoPedido == null || estadoPedido.getIdDetalleCatalogo() == null) {
			throw new RuntimeException("El id del estado del pedido es obligatorio");
		}

		return detalleCatalogoJpaRepositorio.findByIdWithCatalogo(estadoPedido.getIdDetalleCatalogo())
				.orElseThrow(() -> new RuntimeException("Estado del pedido no encontrado"));
	}

	
	

}
