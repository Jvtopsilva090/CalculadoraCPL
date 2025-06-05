package com.github.jvtopsilva090.repository;

import com.github.jvtopsilva090.entity.Historico;

import java.sql.*;
import java.util.*;

public class HistoricoRepository {

    private static final String URL = "jdbc:sqlite:historico.db";

    public HistoricoRepository() {
        criarTabela();
    }

    private void criarTabela() {
        try (
                Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()
        ) {
            String sql = """
                CREATE TABLE IF NOT EXISTS historico (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    operacao TEXT NOT NULL,
                    resultado REAL NOT NULL
                )
            """;

            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar banco de dados: ".concat(e.getMessage()));
        }
    }

    public void inserir(Historico historico) {
        String sql = "INSERT INTO historico (operacao, resultado) VALUES (?, ?)";

        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, historico.getOperacao());
            pstmt.setBigDecimal(2, historico.getResultado());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir no banco de dados: ".concat(e.getMessage()));
        }
    }

    public List<Historico> listar() {
        String sql = "SELECT * FROM historico ORDER BY id DESC";

        try (
                Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            List<Historico> lista = new ArrayList<>();

            while (rs.next()) {
                Historico historico = Historico.builder()
                        .id(rs.getInt("id"))
                        .operacao(rs.getString("operacao"))
                        .resultado(rs.getBigDecimal("resultado"))
                        .build();

                lista.add(historico);
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar banco de dados: ".concat(e.getMessage()));
        }
    }
}
