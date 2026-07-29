package com.uisrael.consumoopticaperfectvisionapi.services.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleExamenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleOrdenResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.PacienteResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.ICertificadoPdfService;

@Service
public class CertificadoPdfServiceImpl implements ICertificadoPdfService {

	private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public byte[] generarCertificadoPdf(ExamenVisualResponseDto examen, DetalleExamenResponseDto detalle) {
		StringBuilder pdf = new StringBuilder();

		// Encabezado institucional
		relleno(pdf, 0, 708, 612, 84, "0.08 0.31 0.52");
		relleno(pdf, 0, 700, 612, 8, "0.12 0.67 0.67");
		pdf.append("q 132 0 0 88 30 708 cm /Logo Do Q\n");
		texto(pdf, "F2", 15, 365, 750, "CERTIFICADO VISUAL", "1 1 1");
		texto(pdf, "F1", 9, 443, 734, "Documento N.º CERT-" + valor(examen.getIdExamen()), "0.85 0.94 1");

		// Información general
		tituloSeccion(pdf, 54, 674, 504, "DATOS DEL CERTIFICADO");
		etiquetaValor(pdf, 60, 646, "Paciente", valor(examen.getPacienteNombre()));
		etiquetaValor(pdf, 320, 646, "Fecha de examen", fecha(examen.getFechaExamen()));
		etiquetaValor(pdf, 60, 624, "N.º de examen", valor(examen.getIdExamen()));
		etiquetaValor(pdf, 320, 624, "Estado", examen.isEstado() ? "Activo" : "Inactivo");
		linea(pdf, 54, 610, 558, 610, "0.78 0.82 0.86", 0.7);

		// Fórmula optométrica
		tituloSeccion(pdf, 54, 582, 504, "FÓRMULA OPTOMÉTRICA");
		if (detalle != null) {
			tablaFormula(pdf, detalle);
			tituloSeccion(pdf, 54, 372, 504, "MEDIDAS COMPLEMENTARIAS");
			cajaMedida(pdf, 54, 322, 160, "Distancia pupilar (DP)", valor(detalle.getDistanciaPupilar()));
			cajaMedida(pdf, 226, 322, 160, "Altura bifocal", valor(detalle.getAlturaBifocal()));
			cajaMedida(pdf, 398, 322, 160, "Altura progresivo", valor(detalle.getAlturaProgresivo()));
		} else {
			borde(pdf, 54, 452, 504, 104, "0.78 0.82 0.86", 0.8);
			texto(pdf, "F1", 11, 174, 500, "No existen medidas registradas para este examen.", "0.35 0.38 0.42");
		}

		// Observaciones
		tituloSeccion(pdf, 54, 284, 504, "DIAGNÓSTICO / OBSERVACIONES");
		relleno(pdf, 54, 174, 504, 88, "0.97 0.98 0.99");
		borde(pdf, 54, 174, 504, 88, "0.78 0.82 0.86", 0.8);
		List<String> observaciones = envolver(valorTexto(examen.getObservaciones()), 82);
		for (int i = 0; i < Math.min(observaciones.size(), 5); i++) {
			texto(pdf, "F1", 10, 66, 244 - (i * 14), observaciones.get(i), "0.18 0.20 0.22");
		}

		// Emisión y firma
		texto(pdf, "F1", 9, 54, 145,
				"Se extiende el presente certificado para los fines que el paciente estime pertinentes.",
				"0.32 0.35 0.38");
		etiquetaValor(pdf, 54, 122, "Fecha de emisión", FECHA.format(LocalDate.now()));
		linea(pdf, 353, 104, 542, 104, "0.20 0.24 0.28", 0.8);
		texto(pdf, "F2", 9, 390, 90, "Profesional responsable", "0.20 0.24 0.28");
		texto(pdf, "F1", 8, 408, 78, "Firma y sello", "0.40 0.43 0.46");

		// Pie de página
		relleno(pdf, 0, 0, 612, 42, "0.08 0.31 0.52");
		texto(pdf, "F1", 8, 54, 17,
				"Perfect Vision  |  Cuidamos tu salud visual  |  Documento generado electrónicamente",
				"0.88 0.94 0.98");

		return construirPdf(pdf.toString(), cargarLogo());
	}

	@Override
	public byte[] generarOrdenPedidoPdf(OrdenPedidoResponseDto pedido, PacienteResponseDto paciente,
			List<DetalleOrdenResponseDto> detalles, Map<Integer, String> catalogosPorId) {
		StringBuilder pdf = new StringBuilder();
		relleno(pdf, 0, 708, 612, 84, "0.08 0.31 0.52");
		relleno(pdf, 0, 700, 612, 8, "0.95 0.55 0.03");
		pdf.append("q 132 0 0 88 30 708 cm /Logo Do Q\n");
		texto(pdf, "F2", 16, 370, 750, "ORDEN DE PEDIDO", "1 1 1");
		texto(pdf, "F1", 10, 445, 733, "Orden N.º " + valor(pedido.getIdPedido()), "0.85 0.94 1");

		tituloSeccion(pdf, 40, 668, 532, "DATOS GENERALES");
		String nombrePaciente = paciente != null
				? valor(paciente.getNombres()) + " " + valor(paciente.getApellidos())
				: valor(pedido.getPacienteNombre());
		etiquetaValor(pdf, 46, 642, "Paciente", abreviar(nombrePaciente, 30));
		etiquetaValor(pdf, 326, 642, "Cédula", paciente != null ? valor(paciente.getCedula()) : "N/A");
		etiquetaValor(pdf, 46, 620, "Examen", abreviar(valor(pedido.getExamenDescripcion()), 30));
		etiquetaValor(pdf, 326, 620, "Estado", valor(pedido.getNombreEstadoPedido()));
		etiquetaValor(pdf, 46, 598, "Fecha pedido", fecha(pedido.getFechaPedido()));
		etiquetaValor(pdf, 326, 598, "Fecha entrega", fecha(pedido.getFechaEntrega()));

		tituloSeccion(pdf, 40, 562, 532, "DETALLE DEL PEDIDO");
		double yCabecera = 532;
		relleno(pdf, 40, yCabecera, 532, 25, "0.12 0.43 0.62");
		String[] titulos = {"N.º", "Producto", "Cant.", "P. Unit.", "Subtotal"};
		double[] posiciones = {46, 82, 382, 442, 510};
		for (int i = 0; i < titulos.length; i++) {
			texto(pdf, "F2", 8, posiciones[i], yCabecera + 8, titulos[i], "1 1 1");
		}

		double y = yCabecera - 23;
		int numeroDetalle = 1;
		for (DetalleOrdenResponseDto detalle : detalles) {
			if (numeroDetalle % 2 == 0) {
				relleno(pdf, 40, y - 5, 532, 22, "0.96 0.98 0.99");
			}
			texto(pdf, "F1", 8, posiciones[0], y, Integer.toString(numeroDetalle), "0.15 0.17 0.19");
			texto(pdf, "F1", 8, posiciones[1], y,
					abreviar(detalle.getProductoNombre() != null ? detalle.getProductoNombre() : "Detalle anterior", 48),
					"0.15 0.17 0.19");
			texto(pdf, "F1", 8, posiciones[2], y,
					valor(detalle.getCantidad()), "0.15 0.17 0.19");
			texto(pdf, "F1", 8, posiciones[3], y, dinero(detalle.getPrecioUnitario()), "0.15 0.17 0.19");
			texto(pdf, "F1", 8, posiciones[4], y, dinero(detalle.getSubtotal()), "0.15 0.17 0.19");
			linea(pdf, 40, y - 6, 572, y - 6, "0.82 0.85 0.88", 0.4);
			y -= 23;
			numeroDetalle++;
		}
		if (detalles.isEmpty()) {
			texto(pdf, "F1", 10, 196, y, "La orden no contiene detalles registrados.", "0.35 0.38 0.42");
			y -= 25;
		}

		java.math.BigDecimal total = detalles.stream()
				.map(DetalleOrdenResponseDto::getSubtotal)
				.reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
		relleno(pdf, 400, y - 8, 172, 28, "0.91 0.95 0.97");
		texto(pdf, "F2", 11, 412, y + 1, "TOTAL: " + dinero(total), "0.08 0.31 0.52");

		relleno(pdf, 0, 0, 612, 42, "0.08 0.31 0.52");
		texto(pdf, "F1", 8, 54, 17,
				"Perfect Vision  |  Miramos a través de tus ojos  |  Documento generado electrónicamente",
				"0.88 0.94 0.98");
		return construirPdf(pdf.toString(), cargarLogo());
	}

	private void tablaFormula(StringBuilder pdf, DetalleExamenResponseDto d) {
		double x = 54;
		double y = 430;
		double alto = 126;
		double[] anchos = {82, 70, 70, 58, 70, 70, 58, 26};
		String[] cabeceras = {"Visión", "Esfera", "Cilindro", "Eje", "Adición OD", "Adición OI", "Ojo", ""};

		relleno(pdf, x, y + alto - 28, 504, 28, "0.12 0.43 0.62");
		borde(pdf, x, y, 504, alto, "0.55 0.62 0.68", 0.8);
		double actual = x;
		for (int i = 0; i < anchos.length; i++) {
			if (i > 0) {
				linea(pdf, actual, y, actual, y + alto, "0.65 0.70 0.74", 0.5);
			}
			if (i < 7) {
				texto(pdf, "F2", 8, actual + 6, y + alto - 18, cabeceras[i], "1 1 1");
			}
			actual += anchos[i];
		}
		linea(pdf, x, y + 49, x + 504, y + 49, "0.65 0.70 0.74", 0.5);
		linea(pdf, x, y + 98, x + 504, y + 98, "0.65 0.70 0.74", 0.5);

		filaFormula(pdf, y + 68, "Distancia",
				d.getEsferaDistanciaOd(), d.getCilindroDistanciaOd(), d.getEjeDistanciaOd(),
				d.getAdicionOd(), d.getAdicionOi(),
				d.getEsferaDistanciaOi(), d.getCilindroDistanciaOi(), d.getEjeDistanciaOi());
		filaFormula(pdf, y + 19, "Lectura",
				d.getEsferaLecturaOd(), d.getCilindroLecturaOd(), d.getEjeLecturaOd(),
				d.getAdicionOd(), d.getAdicionOi(),
				d.getEsferaLecturaOi(), d.getCilindroLecturaOi(), d.getEjeLecturaOi());
	}

	private void filaFormula(StringBuilder pdf, double y, String vision,
			Object esferaOd, Object cilindroOd, Object ejeOd, Object adicionOd, Object adicionOi,
			Object esferaOi, Object cilindroOi, Object ejeOi) {
		texto(pdf, "F2", 9, 60, y + 13, vision, "0.12 0.27 0.38");
		texto(pdf, "F1", 8, 142, y + 24, "OD: " + valor(esferaOd), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 212, y + 24, "OD: " + valor(cilindroOd), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 282, y + 24, "OD: " + valor(ejeOd), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 340, y + 17, valor(adicionOd), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 410, y + 17, valor(adicionOi), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 142, y + 8, "OI: " + valor(esferaOi), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 212, y + 8, "OI: " + valor(cilindroOi), "0.15 0.17 0.19");
		texto(pdf, "F1", 8, 282, y + 8, "OI: " + valor(ejeOi), "0.15 0.17 0.19");
	}

	private void tituloSeccion(StringBuilder pdf, double x, double y, double ancho, String titulo) {
		relleno(pdf, x, y, ancho, 23, "0.91 0.95 0.97");
		texto(pdf, "F2", 10, x + 10, y + 7, titulo, "0.08 0.31 0.52");
	}

	private void etiquetaValor(StringBuilder pdf, double x, double y, String etiqueta, String valor) {
		texto(pdf, "F2", 9, x, y, etiqueta + ":", "0.12 0.31 0.44");
		texto(pdf, "F1", 10, x + 92, y, valor, "0.15 0.17 0.19");
	}

	private void cajaMedida(StringBuilder pdf, double x, double y, double ancho, String titulo, String valor) {
		relleno(pdf, x, y, ancho, 38, "0.97 0.98 0.99");
		borde(pdf, x, y, ancho, 38, "0.75 0.81 0.85", 0.7);
		texto(pdf, "F2", 8, x + 8, y + 23, titulo, "0.16 0.35 0.48");
		texto(pdf, "F1", 11, x + 8, y + 8, valor, "0.12 0.14 0.16");
	}

	private byte[] construirPdf(String contenido, ImagenPdf logo) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			List<Integer> offsets = new ArrayList<>();
			escribir(out, "%PDF-1.4\n");
			objeto(out, offsets, "1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");
			objeto(out, offsets, "2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n");
			objeto(out, offsets, "3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] "
					+ "/Resources << /Font << /F1 4 0 R /F2 5 0 R >> "
					+ "/XObject << /Logo 6 0 R >> >> /Contents 7 0 R >>\nendobj\n");
			objeto(out, offsets, "4 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica "
					+ "/Encoding /WinAnsiEncoding >>\nendobj\n");
			objeto(out, offsets, "5 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold "
					+ "/Encoding /WinAnsiEncoding >>\nendobj\n");

			offsets.add(out.size());
			escribir(out, "6 0 obj\n<< /Type /XObject /Subtype /Image /Width " + logo.ancho()
					+ " /Height " + logo.alto()
					+ " /ColorSpace /DeviceRGB /BitsPerComponent 8 /Filter /FlateDecode /Length "
					+ logo.datos().length + " >>\nstream\n");
			out.write(logo.datos());
			escribir(out, "\nendstream\nendobj\n");

			byte[] bytesContenido = contenido.getBytes(StandardCharsets.ISO_8859_1);
			offsets.add(out.size());
			escribir(out, "7 0 obj\n<< /Length " + bytesContenido.length + " >>\nstream\n");
			out.write(bytesContenido);
			escribir(out, "\nendstream\nendobj\n");

			int xref = out.size();
			escribir(out, "xref\n0 8\n0000000000 65535 f \n");
			for (Integer offset : offsets) {
				escribir(out, String.format("%010d 00000 n \n", offset));
			}
			escribir(out, "trailer\n<< /Size 8 /Root 1 0 R >>\nstartxref\n" + xref + "\n%%EOF");
			return out.toByteArray();
		} catch (Exception ex) {
			throw new RuntimeException("No se pudo generar el PDF del certificado", ex);
		}
	}

	private ImagenPdf cargarLogo() {
		try (InputStream stream = getClass().getResourceAsStream("/static/img/perfect-vision-logo.png")) {
			if (stream == null) {
				throw new IllegalStateException("No se encontró el logotipo de Perfect Vision");
			}
			BufferedImage original = ImageIO.read(stream);
			int ancho = 420;
			int alto = Math.max(1, (int) Math.round(original.getHeight() * (ancho / (double) original.getWidth())));
			BufferedImage reducida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = reducida.createGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			graphics.drawImage(original, 0, 0, ancho, alto, null);
			graphics.dispose();

			byte[] rgb = new byte[ancho * alto * 3];
			int posicion = 0;
			for (int y = 0; y < alto; y++) {
				for (int x = 0; x < ancho; x++) {
					int pixel = reducida.getRGB(x, y);
					rgb[posicion++] = (byte) ((pixel >> 16) & 0xff);
					rgb[posicion++] = (byte) ((pixel >> 8) & 0xff);
					rgb[posicion++] = (byte) (pixel & 0xff);
				}
			}

			ByteArrayOutputStream comprimida = new ByteArrayOutputStream();
			try (DeflaterOutputStream deflater = new DeflaterOutputStream(comprimida)) {
				deflater.write(rgb);
			}
			return new ImagenPdf(ancho, alto, comprimida.toByteArray());
		} catch (Exception ex) {
			throw new RuntimeException("No se pudo cargar el logotipo para el certificado", ex);
		}
	}

	private void objeto(ByteArrayOutputStream out, List<Integer> offsets, String contenido) {
		offsets.add(out.size());
		escribir(out, contenido);
	}

	private void texto(StringBuilder pdf, String fuente, int tamano, double x, double y,
			String contenido, String color) {
		pdf.append("BT /").append(fuente).append(' ').append(tamano).append(" Tf ")
				.append(color).append(" rg ")
				.append(numero(x)).append(' ').append(numero(y)).append(" Td (")
				.append(escapar(contenido)).append(") Tj ET\n");
	}

	private void relleno(StringBuilder pdf, double x, double y, double ancho, double alto, String color) {
		pdf.append(color).append(" rg ").append(numero(x)).append(' ').append(numero(y)).append(' ')
				.append(numero(ancho)).append(' ').append(numero(alto)).append(" re f\n");
	}

	private void borde(StringBuilder pdf, double x, double y, double ancho, double alto,
			String color, double grosor) {
		pdf.append(color).append(" RG ").append(numero(grosor)).append(" w ")
				.append(numero(x)).append(' ').append(numero(y)).append(' ')
				.append(numero(ancho)).append(' ').append(numero(alto)).append(" re S\n");
	}

	private void linea(StringBuilder pdf, double x1, double y1, double x2, double y2,
			String color, double grosor) {
		pdf.append(color).append(" RG ").append(numero(grosor)).append(" w ")
				.append(numero(x1)).append(' ').append(numero(y1)).append(" m ")
				.append(numero(x2)).append(' ').append(numero(y2)).append(" l S\n");
	}

	private List<String> envolver(String texto, int maximo) {
		List<String> lineas = new ArrayList<>();
		StringBuilder linea = new StringBuilder();
		for (String palabra : texto.replace('\n', ' ').split("\\s+")) {
			if (!linea.isEmpty() && linea.length() + palabra.length() + 1 > maximo) {
				lineas.add(linea.toString());
				linea = new StringBuilder();
			}
			if (!linea.isEmpty()) {
				linea.append(' ');
			}
			linea.append(palabra);
		}
		if (!linea.isEmpty()) {
			lineas.add(linea.toString());
		}
		return lineas;
	}

	private void escribir(ByteArrayOutputStream out, String texto) {
		byte[] bytes = texto.getBytes(StandardCharsets.ISO_8859_1);
		out.write(bytes, 0, bytes.length);
	}

	private String escapar(String texto) {
		return texto.replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)");
	}

	private String numero(double valor) {
		return valor == Math.rint(valor) ? Long.toString(Math.round(valor)) : Double.toString(valor);
	}

	private String fecha(LocalDate fecha) {
		return fecha != null ? FECHA.format(fecha) : "N/A";
	}

	private String valor(Object valor) {
		return valor != null ? valor.toString() : "N/A";
	}

	private String valorTexto(String valor) {
		return valor != null && !valor.isBlank() ? valor : "Sin observaciones registradas.";
	}

	private String abreviar(String valor, int maximo) {
		return valor.length() <= maximo ? valor : valor.substring(0, maximo - 3) + "...";
	}

	private String dinero(java.math.BigDecimal valor) {
		return "$ " + (valor != null ? valor.setScale(2, java.math.RoundingMode.HALF_UP) : "0.00");
	}

	private record ImagenPdf(int ancho, int alto, byte[] datos) {
	}
}
