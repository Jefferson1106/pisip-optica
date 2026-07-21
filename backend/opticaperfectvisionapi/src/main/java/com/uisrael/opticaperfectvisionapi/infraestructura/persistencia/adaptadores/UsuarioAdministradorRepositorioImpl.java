package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.UsuarioAdministrador;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IUsuarioAdministradorRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.UsuarioAdministradorEntity;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores.IUsuarioAdministradorJpaMapper;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IDetalleCatalogoJpaRepositorio;
import com.uisrael.opticaperfectvisionapi.infraestructura.repositorios.IUsuarioAdministradorJpaRepositorio;

public class UsuarioAdministradorRepositorioImpl implements IUsuarioAdministradorRepositorio {

	private final IUsuarioAdministradorJpaRepositorio jpaRepositorio;
	private final IUsuarioAdministradorJpaMapper mapper;
	private final IDetalleCatalogoJpaRepositorio detalleCatalogoRepositorio;

	public UsuarioAdministradorRepositorioImpl(IUsuarioAdministradorJpaRepositorio jpaRepositorio,
			IUsuarioAdministradorJpaMapper mapper, IDetalleCatalogoJpaRepositorio detalleCatalogoRepositorio) {
		this.jpaRepositorio = jpaRepositorio;
		this.mapper = mapper;
		this.detalleCatalogoRepositorio = detalleCatalogoRepositorio;
	}

	@Override
	public UsuarioAdministrador guardar(UsuarioAdministrador usuarioAdministrador) {
		UsuarioAdministradorEntity entity = mapper.toEntity(usuarioAdministrador);
		entity.setTipoUsuario(resolverTipoUsuario(entity.getTipoUsuario()));
		UsuarioAdministradorEntity guardado = jpaRepositorio.save(entity);
		return mapper.toDomain(guardado);
	}

	@Override
	public Optional<UsuarioAdministrador> buscarPorId(Integer idUsuario) {
		return jpaRepositorio.findByIdConTipoUsuario(idUsuario).map(mapper::toDomain);
	}

	@Override
	public Optional<UsuarioAdministrador> buscarPorCorreo(String correo) {
		return jpaRepositorio.findByCorreoIgnoreCase(correo).map(mapper::toDomain);
	}

	@Override
	public List<UsuarioAdministrador> listarTodos() {
		return jpaRepositorio.findAllConTipoUsuario().stream().map(mapper::toDomain).toList();
	}

	@Override
	public UsuarioAdministrador actualizar(Integer idUsuario, UsuarioAdministrador usuarioAdministrador) {
		UsuarioAdministradorEntity existente = jpaRepositorio.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario administrador no encontrado"));

		existente.setTipoUsuario(resolverTipoUsuario(usuarioAdministrador.getTipoUsuario()));
		existente.setNombres(usuarioAdministrador.getNombres());
		existente.setApellidos(usuarioAdministrador.getApellidos());
		existente.setCorreo(usuarioAdministrador.getCorreo());
		existente.setContrasenia(usuarioAdministrador.getContrasenia());
		existente.setEstado(usuarioAdministrador.isEstado());
		existente.setIntentosFallidos(usuarioAdministrador.getIntentosFallidos());

		return mapper.toDomain(jpaRepositorio.save(existente));
	}

	@Override
	public UsuarioAdministrador actualizarEstado(Integer idUsuario, boolean estado) {
		UsuarioAdministradorEntity existente = jpaRepositorio.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario administrador no encontrado"));
		existente.setEstado(estado);
		if (estado) {
			existente.setIntentosFallidos(0);
		}
		return mapper.toDomain(jpaRepositorio.save(existente));
	}

	@Override
	public UsuarioAdministrador actualizarIntentosFallidos(Integer idUsuario, int intentosFallidos) {
		UsuarioAdministradorEntity existente = jpaRepositorio.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario administrador no encontrado"));
		existente.setIntentosFallidos(intentosFallidos);
		return mapper.toDomain(jpaRepositorio.save(existente));
	}

	@Override
	public boolean existeCorreo(String correo) {
		return jpaRepositorio.existsByCorreoIgnoreCase(correo);
	}

	@Override
	public boolean existeCorreoParaOtro(String correo, Integer idUsuario) {
		return jpaRepositorio.existsByCorreoIgnoreCaseAndIdUsuarioNot(correo, idUsuario);
	}

	private DetalleCatalogoEntity resolverTipoUsuario(DetalleCatalogoEntity tipoUsuario) {
		if (tipoUsuario == null || tipoUsuario.getIdDetalleCatalogo() == null) {
			throw new RuntimeException("El tipo de usuario es obligatorio");
		}
		return detalleCatalogoRepositorio.findById(tipoUsuario.getIdDetalleCatalogo())
				.orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));
	}
}