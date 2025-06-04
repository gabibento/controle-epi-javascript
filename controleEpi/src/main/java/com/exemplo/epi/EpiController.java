package com.exemplo.epi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EpiController {

    @Autowired
    private EpiRepository epiRepository;

    @PostMapping("/epis")
    public String salvar(@RequestParam String nome, @RequestParam int quantidade) {
        epiRepository.salvar(new Epi(nome, quantidade));
        System.out.println(nome + quantidade);
        return "redirect:/epis";
    }

    @GetMapping("/epis")
    @ResponseBody
    public List<Epi> listar() {
        return epiRepository.buscarTodos();
    }
    
}
