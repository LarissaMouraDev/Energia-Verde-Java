package org.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Relatorio;

public class RelatorioDAO {
    public void inserir(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO relatorios (data_inicio, data_fim, total_residuos, reducao_percentual, tipo_residuo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(relatorio.getDataInicio().getTime()));
            stmt.setDate(2, new java.sql.Date(relatorio.getDataFim().getTime()));
            stmt.setDouble(3, relatorio.getTotalResiduos());
            stmt.setDouble(4, relatorio.getReducaoPercentual());
            stmt.setString(5, relatorio.getTipoResiduo());

            stmt.executeUpdate();
        }
    }

    public void atualizar(Relatorio relatorio) throws SQLException {
        String sql = "UPDATE relatorios SET data_inicio = ?, data_fim = ?, total_residuos = ?, reducao_percentual = ?, tipo_residuo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(relatorio.getDataInicio().getTime()));
            stmt.setDate(2, new java.sql.Date(relatorio.getDataFim().getTime()));
            stmt.setDouble(3, relatorio.getTotalResiduos());
            stmt.setDouble(4, relatorio.getReducaoPercentual());
            stmt.setString(5, relatorio.getTipoResiduo());
            stmt.setInt(6, relatorio.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM relatorios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public List<Relatorio> buscarPorPeriodo(String dataInicio, String dataFim) throws SQLException {
        String sql = "SELECT * FROM relatorios WHERE data_inicio BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')";
        List<Relatorio> relatorios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,dataInicio );
            stmt.setString(2, dataFim);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setId(rs.getInt("id"));
                    relatorio.setDataInicio(rs.getDate("data_inicio"));
                    relatorio.setDataFim(rs.getDate("data_fim"));
                    relatorio.setTotalResiduos(rs.getDouble("total_residuos"));
                    relatorio.setReducaoPercentual(rs.getDouble("reducao_percentual"));
                    relatorio.setTipoResiduo(rs.getString("tipo_residuo"));
                    relatorios.add(relatorio);
                }
            }
        }
        return relatorios;
    }

    public List<Relatorio> listarTodos() throws SQLException {
        String sql = "SELECT * FROM relatorios ORDER BY data_inicio DESC";
        List<Relatorio> relatorios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setDataInicio(rs.getDate("data_inicio"));
                relatorio.setDataFim(rs.getDate("data_fim"));
                relatorio.setTotalResiduos(rs.getDouble("total_residuos"));
                relatorio.setReducaoPercentual(rs.getDouble("reducao_percentual"));
                relatorio.setTipoResiduo(rs.getString("tipo_residuo"));
                relatorios.add(relatorio);
            }
        }
        return relatorios;
    }

    public Relatorio buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM relatorios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setId(rs.getInt("id"));
                    relatorio.setDataInicio(rs.getDate("data_inicio"));
                    relatorio.setDataFim(rs.getDate("data_fim"));
                    relatorio.setTotalResiduos(rs.getDouble("total_residuos"));
                    relatorio.setReducaoPercentual(rs.getDouble("reducao_percentual"));
                    relatorio.setTipoResiduo(rs.getString("tipo_residuo"));
                    return relatorio;
                }
                return null;
            }
        }
    }
}