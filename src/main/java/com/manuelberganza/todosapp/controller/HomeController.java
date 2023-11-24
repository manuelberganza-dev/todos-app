package com.manuelberganza.todosapp.controller;

import com.manuelberganza.todosapp.model.Categoria;
import com.manuelberganza.todosapp.model.Perfil;
import com.manuelberganza.todosapp.model.Tarea;
import com.manuelberganza.todosapp.model.Usuario;
import com.manuelberganza.todosapp.service.ICategoriaService;
import com.manuelberganza.todosapp.service.ITareaService;
import com.manuelberganza.todosapp.service.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    ICategoriaService categoriaService;

    @Autowired
    ITareaService tareaService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String mostrarInicio(Model model) {

        return "index";
    }

    @GetMapping("/crear-todo")
    public String mostrarCrearTodo(Authentication auth, Model model) {
        Usuario usuario = usuarioService.buscarUsuario(auth.getName());
        List<Tarea> tarea = tareaService.buscarTareas(usuario);

        model.addAttribute("categorias", categoriaService.obtenerTodasCategorias());
        model.addAttribute("tareas", tarea);

        return "crear-todo";
    }

    @PostMapping("/crear-todo")
    public String crearTodo(Authentication auth, Tarea tarea) {
        tarea.setUsuario(usuarioService.buscarUsuario(auth.getName()));
        tareaService.guardarTarea(tarea);

        return "redirect:/crear-todo";
    }

    @GetMapping("/editar/{id}")
    public String editarTarea(@PathVariable("id") int idTarea, Model model) {
        model.addAttribute("idTarea", idTarea);
        model.addAttribute("tarea", tareaService.buscarTarea(idTarea));
        model.addAttribute("categorias", categoriaService.obtenerTodasCategorias());

        return "editar";
    }

    @PostMapping("/editar/{id}")
    public String editarTarea(Authentication auth, Tarea tarea) {

        tarea.setUsuario(usuarioService.buscarUsuario(auth.getName()));
        tareaService.guardarTarea(tarea);

        return "redirect:/crear-todo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable("id") int idTarea, Model model) {
        tareaService.eliminarTarea(idTarea);

        return "eliminar";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String mostrarRegistro() { return "registro"; }

    @PostMapping("/signup")
    public String procesarRegistro(Usuario usuario) {

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEstatus(1);

        Perfil perfil = new Perfil();
        perfil.setId(1);
        usuario.agregar(perfil);

        usuarioService.guardarUsuario(usuario);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);

        return "redirect:/";
    }

    @ModelAttribute
    public void setGenericos(Authentication auth, Model model) {
        if (auth != null) {
            model.addAttribute("usuario", auth.getName());
        }
    }
}
