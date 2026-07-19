package com.uisrael.opticaperfectvisionapi.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.PacienteEntity;

public interface IPacienteJpaRepositorio extends JpaRepository<PacienteEntity, Integer>{

	Optional<PacienteEntity> findByCedula(String cedula);
	
	//1807

	    // Buscar por correo
	    Optional<PacienteEntity> findByCorreo(String correo);

	    // Buscar por estado activo
	    List<PacienteEntity> findByActivo(Boolean activo);

	    // Buscar por nombre o apellido parcial
	    @Query("SELECT p FROM PacienteEntity p WHERE p.nombres LIKE %?1% OR p.apellidos LIKE %?1%")
	    List<PacienteEntity> buscarPorNombreOApellido(String texto);

	    // Listar ordenados por fecha de registro
	    @Query("SELECT p FROM PacienteEntity p ORDER BY p.fechaRegistro DESC")
	    List<PacienteEntity> listarTodosOrdenados();
	

}
