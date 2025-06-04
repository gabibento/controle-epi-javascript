package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbc.query("SELECT * FROM devolucoes", (rs, rowNum) -> {
            Epi epi = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Devolucao(epi, usuario, rs.getDate("dataDevolucao").toLocalDate());
        });
    }

    public Devolucao buscarPorDevolucao(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM devolucoes WHERE epi = ? AND usuario LIKE ?";
        return jdbc.queryForObject(sql, new Object[]{epi, usuario}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Devolucao(epi1, usuario1, rs.getDate("dataDevolucao").toLocalDate());
        });
    }

    public List<Devolucao> buscarPorDevolucaoParcial(Epi epi, Usuario usuario) {
        String sql = "SELECT * FROM devolucoes WHERE epi LIKE ? AND usuario LIKE ?";
        return jdbc.query(sql, new Object[]{"%" + epi + "%", "%" + usuario + "%"}, (rs, rowNum) -> {
            Epi epi1 = new Epi(rs.getString("nome"), rs.getInt("quantidade"));
            Usuario usuario1 = new Usuario(rs.getString("nome"), rs.getString("email"));
            return new Devolucao(epi, usuario, rs.getDate("dataDevolucao").toLocalDate());
        });
    }
}