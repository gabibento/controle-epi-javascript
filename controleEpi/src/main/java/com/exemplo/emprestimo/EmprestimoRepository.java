package com.exemplo.emprestimo;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EmprestimoRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (epi, usuario, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        jdbc.update(sql,
                emprestimo.getEpi().getNome(),
                emprestimo.getUsuario().getNome(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }

    public List<Emprestimo> buscarTodos() {
        return jdbc.query("SELECT * FROM emprestimos", (rs, rowNum) -> {
            Epi epi = new Epi(rs.getString("epi"), null);
            Usuario usuario = new Usuario(rs.getString("usuario"), null);

            return new Emprestimo(
                    rs.getDate("dataEmprestimo").toLocalDate(),
                    rs.getDate("dataDevolucao").toLocalDate(),
                    usuario,
                    epi
            );
        });
    }

    public Emprestimo buscarPorNome(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi = ? AND usuario = ?";
        return jdbc.queryForObject(sql,
                new Object[]{epi.getNome(), usuario.getNome()},
                (rs, rowNum) -> {
                    Epi epi1 = new Epi(rs.getString("epi"), null);
                    Usuario usuario1 = new Usuario(rs.getString("usuario"), null);

                    return new Emprestimo(
                            rs.getDate("dataEmprestimo").toLocalDate(),
                            rs.getDate("dataDevolucao").toLocalDate(),
                            usuario1,
                            epi1
                    );
                });
    }

    public List<Emprestimo> buscarPorNomeParcial(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi LIKE ? AND usuario LIKE ?";
        return jdbc.query(sql,
                new Object[]{"%" + epi.getNome() + "%", "%" + usuario.getNome() + "%"},
                (rs, rowNum) -> {
                    Epi epi1 = new Epi(rs.getString("epi"), null);
                    Usuario usuario1 = new Usuario(rs.getString("usuario"), null);

                    return new Emprestimo(
                            rs.getDate("dataEmprestimo").toLocalDate(),
                            rs.getDate("dataDevolucao").toLocalDate(),
                            usuario1,
                            epi1
                    );
                });
    }
}
