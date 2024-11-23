package org.example.DAO;

import org.example.model.Residuos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResiduosDAO {
    public void inserir(Residuos residuos) throws SQLException, SQLException {
        String sql = "INSERT INTO residuos (tipo, descricao, instrucoes_descarte, reciclavel) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residuos.getTipo());
            stmt.setString(2, residuos.getDescricao( ));
            stmt.setString(3, residuos.getInstrucoesDescarte());
            stmt.setBoolean(4, residuos.isReciclavel());

            stmt.executeUpdate();
        }
    }

    public List<Residuos> listarTodos() throws SQLException {
        List<Residuos> residuos = new ArrayList<>();
        String sql = "SELECT * FROM residuos";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Residuos residuos1 = new Residuos();
                residuos1.setId(rs.getInt("id"));
                residuos1.setTipo(rs.getString("tipo"));
                residuos1.setDescricao(rs.getString("descricao"));
                residuos1.setInstrucoesDescarte(rs.getString("instrucoes_descarte"));
                residuos1.setReciclavel(rs.getBoolean("reciclavel"));
                residuos.add(residuos1);
            }
        }
        return residuos;
    }

    public void atualizar(Residuos residuo) throws SQLException {
        int ativo1 ;
        if (residuo.isReciclavel()){
            ativo1 = 1;
        }else{
            ativo1 = 0;
        }
        String sql = "UPDATE residuos SET tipo = ?, descricao = ?, instrucoes_descarte = ?, reciclavel = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residuo.getTipo());
            stmt.setString(2, residuo.getDescricao());
            stmt.setString(3, residuo.getInstrucoesDescarte());
            stmt.setInt(4, ativo1);
            stmt.setInt(5, residuo.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM residuos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Residuos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM residuos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Residuos residuos = new Residuos();
                    residuos.setId(rs.getInt("id"));
                    residuos.setTipo(rs.getString("tipo"));
                    residuos.setDescricao(rs.getString("descricao"));
                    residuos.setInstrucoesDescarte(rs.getString("instrucoes_descarte"));
                    residuos.setReciclavel(rs.getBoolean("reciclavel"));
                    return residuos;
                }
                return null; // Retorna null se não encontrar o resíduo
            }
        }
    }
}


