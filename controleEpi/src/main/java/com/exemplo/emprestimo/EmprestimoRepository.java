package com.exemplo.emprestimo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class EmprestimoRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (epi, usuario, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, emprestimo.getEpi(), emprestimo.getUsuario(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

    public List<Emprestimo> buscarTodos() {
        return jdbc.query("SELECT * FROM emprestimos", new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("epi"), rs.getString("usuario"), rs.getDate("dataEmprestimo"), rs.getDate("dataDevolucao"));
            }
        });
    }

    public Emprestimo buscarPorNome(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi = ? AND usuario LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{epi, usuario}, new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("epi"), rs.getString("usuario"), rs.getDate("dataEmprestimo"), rs.getDate("dataDevolucao"));
            }
        });
    }

    public List<Emprestimo> buscarPorNomeParcial(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM emprestimos WHERE epi LIKE ? AND usuario LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + epi + "%", "%" + usuario + "%"}, new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("epi"), rs.getString("usuario"), rs.getDate("dataEmprestimo"), rs.getDate("dataDevolucao"));
            }
        });
    }
}