package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;

@Controller
@RequestMapping("/view/usuarios")
public class UsuarioViewController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/view/usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return "redirect:/view/usuarios";
    }
} 