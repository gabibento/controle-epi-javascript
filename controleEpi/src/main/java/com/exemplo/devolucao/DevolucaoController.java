package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.epi.EpiRepository;
import com.exemplo.usuario.Usuario;
import com.exemplo.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class    DevolucaoController {

    @Autowired
    private DevolucaoRepository devolucaoRepository;
    @Autowired
    private EpiRepository epiRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/devolucoes")
    public String salvar(@RequestParam String nomeEpi, @RequestParam String emailUsuario, @RequestParam LocalDate dataDevolucao) {
        devolucaoRepository.salvar(new Devolucao(epiRepository.buscarPorNome(nomeEpi), 
        usuarioRepository.buscarPorEmail(emailUsuario), dataDevolucao));
        return "redirect:/devolucoes";
    }

    @GetMapping("/devolucoes")
    @ResponseBody
    public List<DevolucaoDTO> listar(){
        return devolucaoRepository.buscarTodos();
    }
}
