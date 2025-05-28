package com.exemplo.usuario;

import com.exemplo.devolucao.Devolucao;
import com.exemplo.emprestimo.Emprestimo;
import com.exemplo.epi.Epi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO devolucoes (epi, usuario, dataDevolucao) VALUES (?, ?)";
        jdbc.update(sql, usuario.getNome(), usuario.getEmail());
    }

    public List<Emprestimo> buscarTodos() {
        return jdbc.query("SELECT * FROM usuarios", new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("epi"), rs.getString("usuario"), rs.getDate("dataEmprestimo"), rs.getDate("dataDevolucao"));
            }
        });
    }

    public Emprestimo buscarPorNome(String nome, String email) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND email LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{nome, email}, new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("nome"), rs.getString("email"));
            }
        });
    }

    public List<Emprestimo> buscarPorNomeParcial(String nome, String email) {
        String sql = "SELECT * FROM devolucoes WHERE nome LIKE ? AND email LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + nome + "%", "%" + email + "%"}, new RowMapper<Emprestimo>() {
            @Override
            public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Emprestimo(rs.getString("nome"), rs.getString("email"));
            }
        });
    }
}