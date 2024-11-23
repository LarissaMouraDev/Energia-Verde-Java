package org.example.service;

import org.example.DAO.ResiduosDAO;
import org.example.model.Residuos;

import java.util.List;

public class ResiduosService {
    private final ResiduosDAO residuosDAO;

    public ResiduosService() {
        this.residuosDAO = new ResiduosDAO();
    }

    public void cadastrar(Residuos residuo) throws Exception {
        if (residuo.getTipo() == null || residuo.getTipo().trim().isEmpty()) {
            throw new Exception("O tipo do resíduo é obrigatório");
        }

        if (residuo.getDescricao() == null || residuo.getDescricao().trim().isEmpty()) {
            throw new Exception("A descrição do resíduo é obrigatória");
        }

        if (residuo.getInstrucoesDescarte() == null || residuo.getInstrucoesDescarte().trim().isEmpty()) {
            throw new Exception("As instruções de descarte são obrigatórias");
        }

        try {
            residuosDAO.inserir(residuo);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar resíduo: " + e.getMessage());
        }
    }

    public List<Residuos> listar() throws Exception {
        try {
            return residuosDAO.listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao listar resíduos: " + e.getMessage());
        }
    }

    public Residuos buscarPorId(int id) throws Exception {
        try {
            Residuos residuo = residuosDAO.buscarPorId(id);
            if (residuo == null) {
                throw new Exception("Resíduo não encontrado");
            }
            return residuo;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar resíduo: " + e.getMessage());
        }
    }

    public void atualizar(Residuos residuo) throws Exception {
        if (residuo.getId() <= 0) {
            throw new Exception("ID do resíduo inválido");
        }

        try {
            residuosDAO.atualizar(residuo);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar resíduo: " + e.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        try {
            residuosDAO.excluir(id);
        } catch (Exception e) {
            throw new Exception("Erro ao excluir resíduo: " + e.getMessage());
        }
    }
}
