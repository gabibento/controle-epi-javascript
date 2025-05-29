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
        String sql = "INSERT INTO epis (nome, validade) VALUES (?, ?)";
        jdbc.update(sql, epi.getNome(), epi.getValidade());
    }

    public List<Epi> buscarTodos() {
        return jdbc.query("SELECT * FROM epis", new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Epi(rs.getString("nome"), rs.getString("validade"));
            }
        });
    }

    public Epi buscarPorNome(String nome) {
        String sql = "SELECT * FROM epis WHERE nome = ?";
        return jdbc.queryForObject(sql, new Object[]{nome}, new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Epi epi = new Epi(rs.getString("nome"), rs.getString("validade"));
                return epi;
            }
        });
    }

    public List<Epi> buscarPorNomeParcial(String nome) {
        String sql = "SELECT * FROM epis WHERE nome LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + nome + "%"}, new RowMapper<Epi>() {
            @Override
            public Epi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Epi epi = new Epi(rs.getString("nome"), rs.getString("validade"));
                return epi;
            }
        });
    }
}