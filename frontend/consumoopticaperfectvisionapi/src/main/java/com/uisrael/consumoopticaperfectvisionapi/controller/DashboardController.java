package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.ExamenVisualResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenEntregaResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.OrdenPedidoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IExamenVisualService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenEntregaService;
import com.uisrael.consumoopticaperfectvisionapi.services.IOrdenPedido;
import com.uisrael.consumoopticaperfectvisionapi.services.IPacienteService;

@Controller
public class DashboardController {
    private static final Locale LOCALE_ES = Locale.forLanguageTag("es-EC");
    private final IPacienteService pacienteService;
    private final IExamenVisualService examenService;
    private final IOrdenPedido pedidoService;
    private final IOrdenEntregaService entregaService;

    public DashboardController(IPacienteService pacienteService, IExamenVisualService examenService,
            IOrdenPedido pedidoService, IOrdenEntregaService entregaService) {
        this.pacienteService = pacienteService;
        this.examenService = examenService;
        this.pedidoService = pedidoService;
        this.entregaService = entregaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        LocalDate hoy = LocalDate.now();
        List<ExamenVisualResponseDto> examenes = examenService.listarExamenesVisuales();
        List<OrdenPedidoResponseDto> pedidos = pedidoService.listarOrdenPedido();
        List<OrdenEntregaResponseDto> entregas = entregaService.listarOrdenes();

        model.addAttribute("totalPacientes", pacienteService.listarPacientes().size());
        model.addAttribute("examenesMes", examenes.stream().filter(e -> mismoMes(e.getFechaExamen(), hoy)).count());
        model.addAttribute("pedidosPendientes", pedidos.stream().filter(this::esPendiente).count());
        model.addAttribute("entregasMes", entregas.stream().filter(e -> mismoMes(e.getFechaEntrega(), hoy)).count());

        List<String> meses = new ArrayList<>();
        List<Long> examenesPorMes = new ArrayList<>();
        List<Long> pedidosPorMes = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            YearMonth mes = YearMonth.from(hoy).minusMonths(i);
            String nombre = mes.getMonth().getDisplayName(TextStyle.SHORT, LOCALE_ES);
            meses.add(nombre.substring(0, 1).toUpperCase(LOCALE_ES) + nombre.substring(1));
            examenesPorMes.add(examenes.stream().filter(e -> pertenece(e.getFechaExamen(), mes)).count());
            pedidosPorMes.add(pedidos.stream().filter(p -> pertenece(p.getFechaPedido(), mes)).count());
        }

        Map<String, Long> porEstado = new LinkedHashMap<>();
        pedidos.forEach(p -> porEstado.merge(texto(p.getNombreEstadoPedido(), "Sin estado"), 1L, Long::sum));
        model.addAttribute("meses", meses);
        model.addAttribute("examenesPorMes", examenesPorMes);
        model.addAttribute("pedidosPorMes", pedidosPorMes);
        model.addAttribute("estadosPedido", porEstado.keySet());
        model.addAttribute("cantidadPorEstado", porEstado.values());
        model.addAttribute("pedidosRecientes", pedidos.stream()
                .sorted((a, b) -> comparar(b.getFechaPedido(), a.getFechaPedido())).limit(5).toList());
        return "dashboard/index";
    }

    private boolean esPendiente(OrdenPedidoResponseDto pedido) {
        String estado = (texto(pedido.getIdentificadorEstadoPedido(), "") + " "
                + texto(pedido.getNombreEstadoPedido(), "")).toUpperCase(LOCALE_ES);
        return !estado.contains("ENTREG") && !estado.contains("COMPLET") && !estado.contains("CANCEL");
    }

    private static boolean mismoMes(LocalDate fecha, LocalDate referencia) {
        return fecha != null && YearMonth.from(fecha).equals(YearMonth.from(referencia));
    }

    private static boolean pertenece(LocalDate fecha, YearMonth mes) {
        return fecha != null && YearMonth.from(fecha).equals(mes);
    }

    private static int comparar(LocalDate a, LocalDate b) {
        if (a == null && b == null) return 0;
        if (a == null) return 1;
        if (b == null) return -1;
        return a.compareTo(b);
    }

    private static String texto(String valor, String defecto) {
        return valor == null || valor.isBlank() ? defecto : valor;
    }
}
