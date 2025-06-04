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
        String sql = "INSERT INTO emprestimos (epi_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, emprestimo.getEpi().getId(), emprestimo.getUsuario().getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

    public List<Emprestimo> buscarTodos() {
       String sql = """
        SELECT e.nome AS epi_nome, e.quantidade AS epi_quantidade,
               u.nome AS usuario_nome, u.email AS usuario_email,
               emp.data_devolucao
        FROM emprestimos emp
        JOIN epis e ON emp.epi_id = e.id
        JOIN usuarios u ON emp.usuario_id = u.id
    """;

    return jdbc.query(sql, (rs, rowNum) -> {
        Epi epi = new Epi(rs.getString("epi_nome"), rs.getInt("epi_quantidade"));
        Usuario usuario = new Usuario(rs.getString("usuario_nome"), rs.getString("usuario_email"));
        return new Emprestimo(epi, usuario, rs.getDate("data_devolucao").toLocalDate());
    });
    }

    public Emprestimo buscarPorEmprestimo(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi = ? AND usuario LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{epi, usuario}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Emprestimo(epi1, usuario1, rs.getDate("dataDevolucao").toLocalDate());
        });
    }

    public List<Emprestimo> buscarPorEmprestimoParcial(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi LIKE ? AND usuario LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + epi + "%", "%" + usuario + "%"}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Emprestimo(epi, usuario, rs.getDate("dataDevolucao").toLocalDate());
        });
    }
}