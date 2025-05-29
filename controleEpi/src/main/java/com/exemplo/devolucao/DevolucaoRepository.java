package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DevolucaoRepository {

        @Autowired
        private JdbcTemplate jdbc;

        public void salvar(Devolucao devolucao) {
            String sql = "INSERT INTO devolucoes (epi, usuario, dataDevolucao) VALUES (?, ?, ?)";
            jdbc.update(sql, devolucao.getEpi(), devolucao.getUsuario(), devolucao.getDataDevolucao());
        }

        public List<Devolucao> buscarTodos() {
            return jdbc.query("SELECT * FROM devolucoes", new RowMapper<Devolucao>() {
                @Override
                public Devolucao mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Devolucao(rs.getEpi("epi"), rs.getUsuario("usuario"), rs.getDate("dataDevolucao"));
                }
            });
        }

        public Devolucao buscarPorNome(Epi epi, Usuario usuario) {
            String sql = "SELECT * FROM devolucoes WHERE epi = ? AND usuario LIKE ?";
            return jdbc.queryForObject(sql, new Object[]{epi, usuario}, new RowMapper<Devolucao>() {
                @Override
                public Devolucao mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Devolucao(rs.getEpi("epi"), rs.getUsuario("usuario"), rs.getDate("dataDevolucao"));
                }
            });
        }

        public List<Devolucao> buscarPorNomeParcial(Epi epi, Usuario usuario) {
            String sql = "SELECT * FROM devolucoes WHERE epi LIKE ? AND usuario LIKE ?";
            return jdbc.query(sql, new Object[]{"%" + epi + "%", "%" + usuario + "%"}, new RowMapper<Devolucao>() {
                @Override
                public Devolucao mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Devolucao(rs.getEpi("epi"), rs.getUsuario("usuario"), rs.getDate("dataDevolucao"));
                }
            });
        }
    }
}
