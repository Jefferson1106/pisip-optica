package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;

class CertificadoPdfServiceImplTest {

    @Test
    void calculaSubtotalConCantidadYPrecioUnitario() {
        DetalleOrdenResponseDto detalle = detalle(3, "12.345");

        assertEquals(new BigDecimal("37.04"), detalle.getSubtotal());
    }

    @Test
    void imprimeEnElPdfLaSumaDeTodosLosSubtotales() {
        CertificadoPdfServiceImpl servicio = new CertificadoPdfServiceImpl();
        OrdenPedidoResponseDto pedido = new OrdenPedidoResponseDto();
        pedido.setIdPedido(10);

        byte[] pdf = servicio.generarOrdenPedidoPdf(
                pedido,
                null,
                List.of(detalle(2, "10.50"), detalle(3, "2.00")),
                Map.of());

        String contenido = new String(pdf, StandardCharsets.ISO_8859_1);
        assertTrue(contenido.contains("TOTAL: $ 27.00"));
    }

    @Test
    void imprimeGraduacionDeLecturaYMedidasComplementariasEnCertificado() {
        CertificadoPdfServiceImpl servicio = new CertificadoPdfServiceImpl();
        ExamenVisualResponseDto examen = new ExamenVisualResponseDto();
        examen.setIdExamen(20);

        DetalleExamenResponseDto detalle = new DetalleExamenResponseDto();
        detalle.setEsferaLecturaOd(new BigDecimal("1.25"));
        detalle.setCilindroLecturaOi(new BigDecimal("-0.75"));
        detalle.setDistanciaPupilar(new BigDecimal("63.00"));
        detalle.setAlturaBifocal(new BigDecimal("18.00"));
        detalle.setAlturaProgresivo(new BigDecimal("20.00"));

        String contenido = new String(
                servicio.generarCertificadoPdf(examen, detalle), StandardCharsets.ISO_8859_1);

        assertTrue(contenido.contains("OD: +1.25"));
        assertTrue(contenido.contains("OI: -0.75"));
        assertTrue(contenido.contains("63.00"));
        assertTrue(contenido.contains("18.00"));
        assertTrue(contenido.contains("20.00"));
    }

    private DetalleOrdenResponseDto detalle(int cantidad, String precio) {
        DetalleOrdenResponseDto detalle = new DetalleOrdenResponseDto();
        detalle.setProductoNombre("Producto de prueba");
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(new BigDecimal(precio));
        return detalle;
    }
}
