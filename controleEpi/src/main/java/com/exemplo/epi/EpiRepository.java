package com.exemplo.epi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

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
}
