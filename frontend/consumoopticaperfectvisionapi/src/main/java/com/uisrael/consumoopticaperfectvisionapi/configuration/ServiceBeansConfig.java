package com.uisrael.consumoopticaperfectvisionapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleExamen;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleOrden;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.DetalleExamenServicesImpl;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.DetalleOrdenServicesImpl;
import com.uisrael.consumoopticaperfectvisionapi.services.impl.OrdenPedidoServicesImpl;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceBeansConfig {

	@Bean
	IDetalleExamen detalleExamenService(WebClient webClient) {
		return new DetalleExamenServicesImpl(webClient);
	}

	@Bean
	IDetalleOrden detalleOrdenService(WebClient webClient) {
		return new DetalleOrdenServicesImpl(webClient);
	}

	@Bean
	IOrdenPedido ordenPedidoService(WebClient webClient) {
		return new OrdenPedidoServicesImpl(webClient);
	}
}