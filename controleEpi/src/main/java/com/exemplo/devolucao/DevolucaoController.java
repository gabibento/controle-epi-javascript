package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DevolucaoController {

    @Autowired
    private DevolucaoRepository devolucaoRepository;

    @PostMapping("/devolucoes")
    public String salvar(@RequestParam Usuario usuario, @RequestParam Epi epi, @RequestParam LocalDate dataDevolucao) {
        devolucaoRepository.salvar(new Devolucao(epi,usuario,dataDevolucao));
        return "redirect:/devolucoes";
    }

    @GetMapping("/devolucoes")
    @ResponseBody
    public List<Devolucao> listar(){
        return devolucaoRepository.buscarTodos();
    }
}
