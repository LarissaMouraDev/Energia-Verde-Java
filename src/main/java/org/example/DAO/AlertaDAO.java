package org.example.DAO;

import org.example.model.Alerta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class AlertaDAO {
    public void inserir(Alerta alerta) throws SQLException {
        LocalDate date = LocalDate.now ();
        int month = date.getMonthValue ();
        int year = date.getYear ();
        int day = date.getDayOfMonth ();
        String data = year +"-"+ month +"-"+ day ;
        int ativo1 ;
        if (alerta.getAtivo()){
            ativo1 = 1;
        }else{
            ativo1 = 0;
        }

        String sql = "INSERT INTO alertas (tipo, mensagem, data_coleta, bairro, ativo) VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alerta.getTipo());
            stmt.setString(2, alerta.getMensagem());
            stmt.setString(3, data);
            stmt.setString(4, alerta.getBairro());
            stmt.setInt(5, ativo1 );

            stmt.executeUpdate();
        }
    }

    public List<Alerta> listarTodos() throws SQLException {
        List<Alerta> alertas = new ArrayList<>();
        String sql = "SELECT * FROM alertas";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alerta alerta = new Alerta();
                alerta.setId(rs.getInt("id"));
                alerta.setTipo(rs.getString("tipo"));
                alerta.setMensagem(rs.getString("mensagem"));
                alerta.setDataColeta(String.valueOf(rs.getDate("data_coleta")));
                alerta.setBairro(rs.getString("bairro"));
                String ativo1 = rs.getString("ativo");

                if( ativo1 == "1"){
                    alerta.setAtivo(true);
                }else {
                    alerta.setAtivo(false);
                }
                alertas.add(alerta);
            }
        }
        return alertas;
    }

    public void atualizar(Alerta alerta) throws SQLException {
        LocalDate date = LocalDate.now ();
        int month = date.getMonthValue ();
        int year = date.getYear ();
        int day = date.getDayOfMonth ();
        String data = year +"-"+ month +"-"+ day ;

        int ativo1 ;
        if (alerta.getAtivo()){
            ativo1 = 1;
        }else{
            ativo1 = 0;
        }

        String sql = "UPDATE alertas SET tipo = ?, mensagem = ?, data_coleta = TO_DATE(?, 'YYYY-MM-DD'), bairro = ?, ativo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alerta.getTipo());
            stmt.setString(2, alerta.getMensagem());
            stmt.setString(3, data);
            stmt.setString(4, alerta.getBairro());
            stmt.setInt(5, ativo1 );
            stmt.setInt(6, alerta.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM alertas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

