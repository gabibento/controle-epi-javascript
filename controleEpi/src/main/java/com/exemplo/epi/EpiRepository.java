package com.exemplo.epi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EpiRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void salvar(Epi epi) {
        String sql = "INSERT INTO epis (nome, quantidade) VALUES (?, ?)";
        System.out.println(epi);
        jdbc.update(sql, epi.getNome(), epi.getQuantidade());
    }

    public List<Epi> buscarTodos() {
        return jdbc.query("SELECT * FROM epis", new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            }
        });
    }

    public Epi buscarPorEpi(String nome) {
        String sql = "SELECT * FROM epis WHERE nome = ?";
        return jdbc.queryForObject(sql, new Object[]{nome}, new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Epi epi = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
                return epi;
            }
        });
    }

    public List<Epi> buscarPorEpiParcial(String nome) {
        String sql = "SELECT * FROM epis WHERE nome LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + nome + "%"}, new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Epi epi = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
                return epi;
            }
        });
    }

    public void atualizarPorNome(Epi epi) {
        String sql = "UPDATE epis SET nome = ?, validade = ? WHERE nome = ?";
        jdbc.update(sql, epi.getNome(), epi.getQuantidade());
    }

    public void deletarPorNome(String nome) {
        String sql = "DELETE FROM epis WHERE nome = ?";
        jdbc.update(sql, nome);
    }
}