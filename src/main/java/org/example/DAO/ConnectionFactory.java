package org.example.DAO;

import java.sql.*;

public class ConnectionFactory {
    // Dados de conexão com o banco Oracle
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm555136";
    private static final String PASS = "141004";

    // Método para obter conexão
    public static Connection getConnection() throws SQLException {
        try {
            // Registra o driver
            Class.forName(DRIVER);

            // Obtém e retorna a conexão
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle não encontrado", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }

    // Método para fechar a conexão
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    // Método para testar a conexão
    public static boolean testarConexao() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }
}

