package com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.impl;

import java.util.List;

import com.uisrael.opticaperfectvisionapi.aplicacion.casosuso.entrada.IDetalleExamenUseCase;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.Catalogo;
import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleExamen;
import com.uisrael.opticaperfectvisionapi.dominio.repositorios.IDetalleExamenRepositorio;

public class DetalleExamenUseCaseImpl implements IDetalleExamenUseCase {
	
	private final IDetalleExamenRepositorio repositorio;
	
	public DetalleExamenUseCaseImpl(IDetalleExamenRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}

	@Override
	public DetalleExamen guardar(DetalleExamen nuevoDetalleExamn) {
		return repositorio.guardar(nuevoDetalleExamn);
	}

	@Override
	public DetalleExamen buscarPorId(int idDetalleExamen) {
		return repositorio.buscarPorId(idDetalleExamen).orElseThrow(()-> new RuntimeException("Examen no encontrado"));
		
	}

	@Override
	public List<DetalleExamen> listarTodos() {
		return repositorio.listarTodos();
	}




	@Override
	public boolean existeDescripcion(String descripcionExamen) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeNombreParaOtroDetalle(String nombreDetalle, int idDetalleExamen) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DetalleExamen actualizar(int idDetalleExamen, DetalleExamen detalleExamen) {
		repositorio.buscarPorId(idDetalleExamen).orElseThrow(()-> new RuntimeException("Detalle examen no encontrado"));
		
		DetalleExamen actualizado = new DetalleExamen(idDetalleExamen, detalleExamen.getExamenVisual(), detalleExamen.getEsferaDistanciaOd(), detalleExamen.getCilindroDistanciaOd(), detalleExamen.getEjeDistanciaOd(),
				detalleExamen.getEsferaDistanciaOi(),detalleExamen.getCilindroDistanciaOi(), detalleExamen.getEjeDistanciaOi(), detalleExamen.getAdicionOd(), detalleExamen.getAdicionOi(), detalleExamen.getDistanciaPupilar(),
				detalleExamen.getAlturaBifocal(),detalleExamen.getAlturaProgresivo(), detalleExamen.getEsferaLecturaOd(),detalleExamen.getCilindroLecturaOd(), detalleExamen.getEjeLecturaOd(),detalleExamen.getEsferaLecturaOi(),
				detalleExamen.getCilindroLecturaOi(), detalleExamen.getEjeLecturaOi());
		
		return repositorio.actualizar(idDetalleExamen, actualizado);
	}

}
