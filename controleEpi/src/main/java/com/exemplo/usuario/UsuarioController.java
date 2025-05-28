package com.exemplo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuarios")
    public String salvar(@RequestParam String nome, @RequestParam String email){
        usuarioRepository.salvar(new Usuario(nome,email));
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    @ResponseBody
    public List<Usuario> listar(){
        return usuarioRepository.buscarTodos();
    }
}
