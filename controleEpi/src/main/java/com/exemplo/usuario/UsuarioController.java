package com.exemplo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping()
    public String salvar(@RequestParam String nome, @RequestParam String email){
        usuarioRepository.salvar(new Usuario(nome, email));
        return "redirect:/usuarios";
    }

    @GetMapping()
    @ResponseBody
    public List<Usuario> listar(){
        return usuarioRepository.buscarTodos();
    }
    @PostMapping("/atualizarUsuario")
    public String atualizar(@RequestParam String email, @RequestParam String nome, @RequestParam String novoEmail) {
        usuarioRepository.atualizarPorEmail(new Usuario(nome, novoEmail), email);
         return "redirect:/usuarios";
    }
}
