package com.exemplo.epi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/epis")
public class EpiController {

    @Autowired
    private EpiRepository epiRepository;

    @PostMapping()
    public String salvar(@RequestParam String nome, @RequestParam int quantidade) {
        if (nome == null || nome.trim().isEmpty() || quantidade < 0) {
        return "redirect:/erro";
    }
        epiRepository.salvar(new Epi(nome, quantidade));
        return "redirect:/epis";
    }

    @GetMapping()
    @ResponseBody
    public List<Epi> listar() {
        return epiRepository.buscarTodos();
    }

    @PostMapping("/atualizarEpi")
  public String atualizar(@RequestParam String nome, @RequestParam String novoNome, @RequestParam int novaQuantidade) {
        epiRepository.atualizarPorNome(new Epi(novoNome, novaQuantidade), nome);
         return "redirect:/epis";
    }
    
    
}
