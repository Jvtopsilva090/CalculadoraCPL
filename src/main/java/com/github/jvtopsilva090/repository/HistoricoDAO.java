package com.github.jvtopsilva090.repository;

import com.github.jvtopsilva090.model.HistoricoModel;

import java.sql.*;
import java.util.*;

public class HistoricoDAO {
    private static final String URL = "jdbc:sqlite:historico.db";

    public HistoricoDAO() {
        criarTabela();
    }

    private void criarTabela() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS historico (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "operacao TEXT NOT NULL, " +
                         "resultado REAL NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(HistoricoModel historico) {
        String sql = "INSERT INTO historico (operacao, resultado) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, historico.getOperacao());
            pstmt.setDouble(2, historico.getResultado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HistoricoModel> listar() {
        List<HistoricoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM historico ORDER BY id DESC";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HistoricoModel historico = new HistoricoModel();
                historico.setId(rs.getInt("id"));
                historico.setOperacao(rs.getString("operacao"));
                historico.setResultado(rs.getDouble("resultado"));
                lista.add(historico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
