package com.exemplo.emprestimo;

import com.exemplo.epi.Epi;
import com.exemplo.epi.EpiRepository;
import com.exemplo.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private EpiRepository epiRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Epi epi;

    @PostMapping("/emprestimos")
    public String salvar(@RequestParam String nomeEpi, @RequestParam String emailUsuario, @RequestParam int quantidade, @RequestParam LocalDate dataDevolucao) {
        if (epi.getQuantidade() <= quantidade) {
            return "Erro: A quantidade solicitada excede a quantidade em estoque.";
        }
        emprestimoRepository.salvar(new Emprestimo(epiRepository.buscarPorNome(nomeEpi),
                usuarioRepository.buscarPorEmail(emailUsuario), dataDevolucao));
        return "redirect:/emprestimos";
    }

    @GetMapping("/emprestimos")
    @ResponseBody
    public List<EmprestimoDTO> listar() {
        return emprestimoRepository.buscarTodosDTO();
    }
}
