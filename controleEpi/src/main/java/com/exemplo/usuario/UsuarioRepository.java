package com.exemplo.usuario;

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

    public List<Usuario> buscarTodos() {
        return jdbc.query("SELECT * FROM usuarios", new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(rs.getString("nome"), rs.getString("email"));
            }
        });
    }

    public Usuario buscarPorUsuario(String nome, String email) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND email LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{nome, email}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(rs.getString("nome"), rs.getString("email"));
            }
        });
    }

    public List<Usuario> buscarPorUsuarioParcial(String nome, String email) {
        String sql = "SELECT * FROM devolucoes WHERE nome LIKE ? AND email LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + nome + "%", "%" + email + "%"}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(rs.getString("nome"), rs.getString("email"));
            }
        });
    }
}