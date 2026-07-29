package com.uisrael.consumoopticaperfectvisionapi.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.request.UsuarioAdministradorUpdateRequestDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.DetalleCatalogoResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.model.dto.response.UsuarioAdministradorResponseDto;
import com.uisrael.consumoopticaperfectvisionapi.services.IDetalleCatalogoService;
import com.uisrael.consumoopticaperfectvisionapi.services.IUsuarioAdministradorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioAdministradorService servicioUsuarios;
    private final IDetalleCatalogoService servicioDetalleCatalogo;

    public UsuarioController(IUsuarioAdministradorService servicioUsuarios,
            IDetalleCatalogoService servicioDetalleCatalogo) {
        this.servicioUsuarios = servicioUsuarios;
        this.servicioDetalleCatalogo = servicioDetalleCatalogo;
    }

    @GetMapping
    public String listar(Model model) {
        List<UsuarioAdministradorResponseDto> usuarios = servicioUsuarios.listarUsuarios().stream()
            .sorted(Comparator.comparing(UsuarioAdministradorResponseDto::getIdUsuario,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed())
            .toList();
        model.addAttribute("listausuarios", usuarios);
        return "usuarios/listarusuario";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioAdministradorRequestDto());
        cargarTiposUsuario(model);
        configurarFormulario(model, false);
        return "usuarios/crearUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute UsuarioAdministradorRequestDto usuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", obtenerMensajesValidacion(bindingResult));
            model.addAttribute("usuario", usuario);
            cargarTiposUsuario(model);
            configurarFormulario(model, false);
            return "usuarios/crearUsuario";
        }
        try {
            servicioUsuarios.guardarUsuario(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario administrador registrado correctamente");
            return "redirect:/usuarios";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            cargarTiposUsuario(model);
            configurarFormulario(model, false);
            return "usuarios/crearUsuario";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        UsuarioAdministradorResponseDto usuarioActual = servicioUsuarios.buscarPorId(id);
        UsuarioAdministradorUpdateRequestDto usuario = new UsuarioAdministradorUpdateRequestDto();
        usuario.setIdTipoUsuario(usuarioActual.getIdTipoUsuario());
        usuario.setNombres(usuarioActual.getNombres());
        usuario.setApellidos(usuarioActual.getApellidos());
        usuario.setCorreo(usuarioActual.getCorreo());
        usuario.setEstado(usuarioActual.isEstado());
        model.addAttribute("idUsuario", id);
        model.addAttribute("usuario", usuario);
        cargarTiposUsuario(model);
        configurarFormulario(model, true);
        return "usuarios/crearUsuario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Integer id,
            @Valid @ModelAttribute UsuarioAdministradorUpdateRequestDto usuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", obtenerMensajesValidacion(bindingResult));
            model.addAttribute("idUsuario", id);
            model.addAttribute("usuario", usuario);
            cargarTiposUsuario(model);
            configurarFormulario(model, true);
            return "usuarios/crearUsuario";
        }
        try {
            if (usuario.getContrasenia() != null && usuario.getContrasenia().isBlank()) {
                usuario.setContrasenia(null);
            }
            servicioUsuarios.actualizarUsuario(id, usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario administrador actualizado correctamente");
            return "redirect:/usuarios";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("idUsuario", id);
            model.addAttribute("usuario", usuario);
            cargarTiposUsuario(model);
            configurarFormulario(model, true);
            return "usuarios/crearUsuario";
        }
    }

    private void cargarTiposUsuario(Model model) {
        List<DetalleCatalogoResponseDto> tiposUsuario = servicioDetalleCatalogo.listarDetalleCatalogos().stream()
            .filter(detalle -> detalle.getIdentificador() != null
                && "ROL".equalsIgnoreCase(detalle.getIdentificador().trim())
                && detalle.isEstado())
            .toList();
        model.addAttribute("listatipousuario", tiposUsuario);
    }

    private void configurarFormulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
    }

    private String obtenerMensajesValidacion(BindingResult bindingResult) {
        String mensajes = bindingResult.getAllErrors().stream()
            .map(error -> error.getDefaultMessage())
            .filter(mensaje -> mensaje != null && !mensaje.isBlank())
            .distinct()
            .collect(Collectors.joining(". "));
        return mensajes.isBlank() ? "Revise los datos ingresados en el formulario" : mensajes;
    }
}
