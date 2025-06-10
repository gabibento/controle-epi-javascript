package com.exemplo.usuario;

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
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
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

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
         return jdbc.queryForObject(sql, new Object[]{email}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("email"));
                return usuario;
            }
        });
    }

    public List<Usuario> buscarPorNomeOuEmailParcial(String nome, String email) {
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ? OR email = ?";
        return jdbc.query(sql, new Object[]{"%" + nome + "%", "%" + email + "%"}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(rs.getString("nome"), rs.getString("email"));
            }
        });
    }

    public void atualizarPorEmail(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE email = ?";
        jdbc.update(sql, usuario.getNome(), usuario.getEmail());
    }

    public void deletarPorEmail(String email) {
        String sql = "DELETE FROM usuarios WHERE email = ?";
        jdbc.update(sql, email);
    }
}