package com.exemplo.emprestimo;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.List;

@Controller
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @PostMapping("/emprestimos")
    public String salvar(@RequestParam Epi epi, @RequestParam Usuario usuario, @RequestParam LocalTime dataEmprestimo, @RequestParam LocalTime dataDevolucao) {
        emprestimoRepository.salvar(new Emprestimo(epi, usuario, dataEmprestimo, dataDevolucao));
        return "redirect:/emprestimos";
    }

    @GetMapping("/emprestimos")
    @ResponseBody
    public List<Emprestimo> listar() {
        return emprestimoRepository.buscarTodos();
    }
}
