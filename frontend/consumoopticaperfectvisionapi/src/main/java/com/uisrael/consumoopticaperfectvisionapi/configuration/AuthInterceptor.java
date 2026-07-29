package com.uisrael.consumoopticaperfectvisionapi.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.uisrael.consumoopticaperfectvisionapi.controller.AuthController;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.LoginResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final List<String> RUTAS_ADMIN = List.of(
			"/usuarios", "/catalogo", "/detalle-catalogo", "/proveedores", "/productos");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		HttpSession session = request.getSession(false);
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (session != null && session.getAttribute(AuthController.SESSION_USER) != null) {
			LoginResponseDto usuario = (LoginResponseDto) session.getAttribute(AuthController.SESSION_USER);
			if (usuario.isRequiereCambioContrasenia() && !path.startsWith("/cambiar-contrasenia")) {
				response.sendRedirect(request.getContextPath() + "/cambiar-contrasenia");
				return false;
			}

			if ("/acceso-denegado".equals(path)) {
				return true;
			}

			if (esRutaAdmin(path) && !esUsuarioAdmin(session)) {
				response.sendRedirect(request.getContextPath() + "/acceso-denegado");
				return false;
			}

			session.setAttribute(AuthController.SESSION_LAST_ACCESS,
					LocalDateTime.now().format(formatter));
			return true;
		}

		boolean expiredSession = request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
		String loginTarget = expiredSession ? "/login?expired=true" : "/login";
		response.sendRedirect(request.getContextPath() + loginTarget);
		return false;
	}

	private boolean esRutaAdmin(String path) {
		return RUTAS_ADMIN.stream().anyMatch(path::startsWith);
	}

	private boolean esUsuarioAdmin(HttpSession session) {
		Object usuarioSesion = session.getAttribute(AuthController.SESSION_USER);
		if (!(usuarioSesion instanceof LoginResponseDto usuario)) {
			return false;
		}
		String rol = usuario.getTipoUsuarioNombre();
		return rol != null && rol.toUpperCase().contains("ADMIN");
	}
}
