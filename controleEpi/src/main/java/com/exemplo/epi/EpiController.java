package com.exemplo.epi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class EpiController {

    @Autowired
    private EpiRepository epiRepository;

    @PostMapping("/epis")
    public String salvar(@RequestParam String nome, @RequestParam String validade) {
        epiRepository.salvar(new Epi(nome, validade));
        System.out.println(nome + validade);
        return "redirect:/epis";
    }

    @GetMapping("/epis")
    @ResponseBody
    public List<Epi> listar() {
        return epiRepository.buscarTodos();
    }
    
}
