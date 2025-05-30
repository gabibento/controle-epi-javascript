package com.exemplo.emprestimo;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class EmprestimoRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO devolucoes (epi, usuario, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, emprestimo.getEpi(), emprestimo.getUsuario(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

    public List<Emprestimo> buscarTodos() {
        return jdbc.query("SELECT * FROM emprestimos", (rs, rowNum) -> {
            Epi epi = new Epi(rs.getString("nome"), rs.getString("validade"));
            Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Emprestimo(epi, usuario,rs.getDate("dataEmprestimo").toLocalDate(), rs.getDate("dataDevolucao").toLocalDate());
        });
    }

    public Emprestimo buscarPorEmprestimo(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi = ? AND usuario LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{epi, usuario}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getString("validade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Emprestimo(epi1, usuario1, rs.getDate("dataEmprestimo").toLocalDate(), rs.getDate("dataDevolucao").toLocalDate());
        });
    }

    public List<Emprestimo> buscarPorEmprestimoParcial(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi LIKE ? AND usuario LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + epi + "%", "%" + usuario + "%"}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getString("validade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Emprestimo(epi, usuario, rs.getDate("dataEmprestimo").toLocalDate(), rs.getDate("dataDevolucao").toLocalDate());
        });
    }
}