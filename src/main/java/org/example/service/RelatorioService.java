package org.example.service;

import org.example.DAO.RelatorioDAO;
import org.example.model.Relatorio;

import java.sql.Date;
import java.util.List;

public class RelatorioService {
    private RelatorioDAO relatorioDAO;

    public RelatorioService() {
        this.relatorioDAO = new RelatorioDAO();
    }

    public void cadastrar(Relatorio relatorio) throws Exception {
        validarRelatorio(relatorio);

        try {
            relatorioDAO.inserir(relatorio);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar relatório: " + e.getMessage());
        }
    }

    private void validarRelatorio(Relatorio relatorio) throws Exception {
        if (relatorio.getDataInicio() == null) {
            throw new Exception("A data inicial é obrigatória");
        }

        if (relatorio.getDataFim() == null) {
            throw new Exception("A data final é obrigatória");
        }

        if (relatorio.getDataFim().before(relatorio.getDataInicio())) {
            throw new Exception("A data final não pode ser anterior à data inicial");
        }

        if (relatorio.getTipoResiduo() == null || relatorio.getTipoResiduo().trim().isEmpty()) {
            throw new Exception("O tipo de resíduo é obrigatório");
        }

        if (relatorio.getTotalResiduos() < 0) {
            throw new Exception("O total de resíduos não pode ser negativo");
        }
    }

    public List<Relatorio> listar() throws Exception {
        try {
            return relatorioDAO.listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao listar relatórios: " + e.getMessage());
        }
    }

    public List<Relatorio> buscarPorPeriodo(String inicio, String fim) throws Exception {
        if (inicio == null || fim == null) {
            throw new Exception("As datas de início e fim são obrigatórias");
        }

        try {
            return relatorioDAO.buscarPorPeriodo(inicio, fim);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar relatórios por período: " + e.getMessage());
        }
    }

    public Relatorio buscarPorId(int id) throws Exception {
        try {
            Relatorio relatorio = relatorioDAO.buscarPorId(id);
            if (relatorio == null) {
                throw new Exception("Relatório não encontrado");
            }
            return relatorio;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar relatório: " + e.getMessage());
        }
    }

    public void atualizar(Relatorio relatorio) throws Exception {
        if (relatorio.getId() <= 0) {
            throw new Exception("ID do relatório inválido");
        }

        validarRelatorio(relatorio);

        try {
            relatorioDAO.atualizar(relatorio);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar relatório: " + e.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        try {
            relatorioDAO.excluir(id);
        } catch (Exception e) {
            throw new Exception("Erro ao excluir relatório: " + e.getMessage());
        }
    }

    public double calcularReducaoTotal(String inicio, String fim) throws Exception {
        try {
            List<Relatorio> relatorios = relatorioDAO.buscarPorPeriodo(inicio, fim);
            return relatorios.stream()
                    .mapToDouble(Relatorio::getReducaoPercentual)
                    .average()
                    .orElse(0.0);
        } catch (Exception e) {
            throw new Exception("Erro ao calcular redução total: " + e.getMessage());
        }
    }
}

