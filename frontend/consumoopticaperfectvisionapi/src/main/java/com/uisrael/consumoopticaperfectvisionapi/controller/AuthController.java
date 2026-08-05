package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorLoginRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRecuperacionRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.CambioContraseniaInicialRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.LoginResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IAuthService;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class AuthController {

	public static final String SESSION_USER = "usuarioSesion";
	public static final String SESSION_LOGIN_AT = "inicioSesion";
	public static final String SESSION_LAST_ACCESS = "ultimaActividad";

	private final IAuthService authService;
	private final int sessionTimeoutSeconds;

	public AuthController(IAuthService authService,
			@Value("${auth.session.timeout-seconds:900}") int sessionTimeoutSeconds) {
		this.authService = authService;
		this.sessionTimeoutSeconds = Math.max(60, sessionTimeoutSeconds);
	}

	@GetMapping("/")
	public String root(HttpSession session) {
		Object usuarioSesion = session.getAttribute(SESSION_USER);
		if (usuarioSesion instanceof LoginResponseDto usuario) {
			if (usuario.isRequiereCambioContrasenia()) {
				return "redirect:/cambiar-contrasenia";
			}
			return "redirect:" + resolverRutaInicio(usuario);
		}
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginForm(@RequestParam(required = false) Boolean expired, Model model, HttpSession session) {
		Object usuarioSesion = session.getAttribute(SESSION_USER);
		if (usuarioSesion instanceof LoginResponseDto usuario) {
			if (usuario.isRequiereCambioContrasenia()) {
				return "redirect:/cambiar-contrasenia";
			}
			return "redirect:" + resolverRutaInicio(usuario);
		}
		if (Boolean.TRUE.equals(expired) && !model.containsAttribute("info")) {
			model.addAttribute("info", "Tu sesion expiro por inactividad. Inicia sesion nuevamente.");
		}
		if (!model.containsAttribute("credenciales")) {
			model.addAttribute("credenciales", new UsuarioAdministradorLoginRequestDto());
		}
		return "auth/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("credenciales") UsuarioAdministradorLoginRequestDto credenciales,
						BindingResult bindingResult,
						HttpSession session,
						Model model,
						RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Revise los datos ingresados");
			return "auth/login";
		}
		try {
			String fechaSesion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LoginResponseDto usuario = authService.login(credenciales);
			if (usuario == null || !usuario.isEstado()) {
				throw new RuntimeException("El usuario se encuentra inactivo. Contacte al administrador.");
			}
			session.setMaxInactiveInterval(sessionTimeoutSeconds);
			session.setAttribute(SESSION_USER, usuario);
			session.setAttribute(SESSION_LOGIN_AT, fechaSesion);
			session.setAttribute(SESSION_LAST_ACCESS, fechaSesion);
			if (usuario.isRequiereCambioContrasenia()) {
				return "redirect:/cambiar-contrasenia";
			}
			return "redirect:" + resolverRutaInicio(usuario);
		} catch (RuntimeException e) {
			String error = e.getMessage() != null ? e.getMessage() : "No fue posible iniciar sesion";
			if (error.toLowerCase().contains("bloque")) {
				redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
				redirectAttributes.addFlashAttribute("correoBloqueado", credenciales.getCorreo());
			}
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("credenciales", credenciales);
			return "redirect:/login";
		}
	}

	private String resolverRutaInicio(LoginResponseDto usuario) {
		return "/dashboard";
	}

	@GetMapping("/recuperar-contrasenia")
	public String recuperarForm(@RequestParam(required = false) String correo, Model model) {
		if (!model.containsAttribute("recuperacion")) {
			UsuarioAdministradorRecuperacionRequestDto recuperacion = new UsuarioAdministradorRecuperacionRequestDto();
			if (correo != null && !correo.isBlank()) {
				recuperacion.setCorreo(correo.trim());
			}
			model.addAttribute("recuperacion", recuperacion);
		}
		return "auth/recuperarContrasenia";
	}

	@GetMapping("/acceso-denegado")
	public String accesoDenegado() {
		return "auth/accesoDenegado";
	}

	@PostMapping("/recuperar-contrasenia")
	public String recuperarContrasenia(
			@Valid @ModelAttribute("recuperacion") UsuarioAdministradorRecuperacionRequestDto request,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Revise el correo ingresado");
			return "auth/recuperarContrasenia";
		}
		try {
			String message = authService.recuperarContrasenia(request);
			redirectAttributes.addFlashAttribute("success", message);
			return "redirect:/login";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			redirectAttributes.addFlashAttribute("recuperacion", request);
			return "redirect:/recuperar-contrasenia";
		}
	}

	@PostMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		session.invalidate();
		response.setHeader("Clear-Site-Data", "\"cache\"");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		redirectAttributes.addFlashAttribute("success", "Sesion cerrada correctamente");
		return "redirect:/login";
	}

	@GetMapping("/cambiar-contrasenia")
	public String cambiarContraseniaForm(Model model, HttpSession session) {
		LoginResponseDto usuario = (LoginResponseDto) session.getAttribute(SESSION_USER);
		if (!usuario.isRequiereCambioContrasenia()) {
			return "redirect:" + resolverRutaInicio(usuario);
		}
		if (!model.containsAttribute("cambioContrasenia")) {
			model.addAttribute("cambioContrasenia", new CambioContraseniaInicialRequestDto());
		}
		return "auth/cambiarContrasenia";
	}

	@PostMapping("/cambiar-contrasenia")
	public String cambiarContrasenia(
			@Valid @ModelAttribute("cambioContrasenia") CambioContraseniaInicialRequestDto request,
			BindingResult bindingResult,
			HttpSession session,
			Model model,
			RedirectAttributes redirectAttributes) {
		LoginResponseDto usuario = (LoginResponseDto) session.getAttribute(SESSION_USER);
		if (bindingResult.hasErrors()) {
			return "auth/cambiarContrasenia";
		}
		if (!request.getNuevaContrasenia().equals(request.getConfirmarContrasenia())) {
			model.addAttribute("error", "Las contraseñas no coinciden");
			return "auth/cambiarContrasenia";
		}
		try {
			LoginResponseDto actualizado = authService.cambiarContraseniaInicial(usuario.getIdUsuario(), request);
			session.setAttribute(SESSION_USER, actualizado);
			redirectAttributes.addFlashAttribute("success", "Contraseña actualizada correctamente");
			return "redirect:" + resolverRutaInicio(actualizado);
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "auth/cambiarContrasenia";
		}
	}
}
