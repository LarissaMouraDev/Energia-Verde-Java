package org.example.service;

import org.example.DAO.AlertaDAO;
import org.example.model.Alerta;
import org.jvnet.hk2.annotations.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlertaService {
    private AlertaDAO alertaDAO;

    public AlertaService() {
        this.alertaDAO = new AlertaDAO();
    }

    public void cadastrar(Alerta alerta) throws Exception {
        validarAlerta(alerta);
        try {
            alertaDAO.listarTodos ();
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar alerta: " + e.getMessage());
        }
    }

    public List<Alerta> listar() throws Exception {
        try {
            return alertaDAO.listarTodos();
        } catch (SQLException e) {
            throw new Exception("Erro ao listar alertas: " + e.getMessage());
        }
    }

    public void atualizar(Alerta alerta) throws Exception {
        if (alerta.getId() <= 0) {
            throw new Exception("ID do alerta inválido");
        }
        validarAlerta(alerta);
        try {
            alertaDAO.atualizar(alerta);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar alerta: " + e.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        try {
            alertaDAO.excluir(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao excluir alerta: " + e.getMessage());
        }
    }

    private void validarAlerta(Alerta alerta) throws Exception {
        if (alerta.getTipo() == null || alerta.getTipo().trim().isEmpty()) {
            throw new Exception("O tipo do alerta é obrigatório");
        }
        if (alerta.getMensagem() == null || alerta.getMensagem().trim().isEmpty()) {
            throw new Exception("A mensagem é obrigatória");
        }
        if (alerta.getDataColeta() == null) {
            throw new Exception("A data de coleta é obrigatória");
        }
        if (alerta.getBairro() == null || alerta.getBairro().trim().isEmpty()) {
            throw new Exception("O bairro é obrigatório");
        }
    }
}
