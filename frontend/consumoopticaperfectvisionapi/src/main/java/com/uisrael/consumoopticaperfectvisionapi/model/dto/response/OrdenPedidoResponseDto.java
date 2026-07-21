package com.uisrael.consumoopticaperfectvisionapi.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class OrdenPedidoResponseDto {
	
	 	private Integer idPedido;
	    private Integer idExamen;
	    private Integer idPaciente;
		    private String pacienteNombre;
		    private String examenDescripcion;
		    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    private LocalDate fechaPedido;
		    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    private LocalDate fechaEntrega;
	    private Integer idEstadoPedido;
	    private String nombreEstadoPedido;
	    private String identificadorEstadoPedido;
		    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime fechaRegistro;
	    
		public Integer getIdPedido() {
			return idPedido;
		}
		public void setIdPedido(Integer idPedido) {
			this.idPedido = idPedido;
		}
		public Integer getIdExamen() {
			return idExamen;
		}
		public void setIdExamen(Integer idExamen) {
			this.idExamen = idExamen;
		}
		public Integer getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(Integer idPaciente) {
			this.idPaciente = idPaciente;
		}
		public String getPacienteNombre() {
			return pacienteNombre;
		}
		public void setPacienteNombre(String pacienteNombre) {
			this.pacienteNombre = pacienteNombre;
		}
		public String getExamenDescripcion() {
			return examenDescripcion;
		}
		public void setExamenDescripcion(String examenDescripcion) {
			this.examenDescripcion = examenDescripcion;
		}
		public LocalDate getFechaPedido() {
			return fechaPedido;
		}
		public void setFechaPedido(LocalDate fechaPedido) {
			this.fechaPedido = fechaPedido;
		}
		public LocalDate getFechaEntrega() {
			return fechaEntrega;
		}
		public void setFechaEntrega(LocalDate fechaEntrega) {
			this.fechaEntrega = fechaEntrega;
		}
		public Integer getIdEstadoPedido() {
			return idEstadoPedido;
		}
		public void setIdEstadoPedido(Integer idEstadoPedido) {
			this.idEstadoPedido = idEstadoPedido;
		}
		public String getNombreEstadoPedido() {
			return nombreEstadoPedido;
		}
		public void setNombreEstadoPedido(String nombreEstadoPedido) {
			this.nombreEstadoPedido = nombreEstadoPedido;
		}
		public String getIdentificadorEstadoPedido() {
			return identificadorEstadoPedido;
		}
		public void setIdentificadorEstadoPedido(String identificadorEstadoPedido) {
			this.identificadorEstadoPedido = identificadorEstadoPedido;
		}
		public LocalDateTime getFechaRegistro() {
			return fechaRegistro;
		}
		public void setFechaRegistro(LocalDateTime fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
	    
	    

}
