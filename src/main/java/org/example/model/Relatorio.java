package org.example.model;

import java.sql.Date;

public class Relatorio {
    private int id;
    private Date dataInicio;
    private Date dataFim;
    private double totalResiduos;
    private double reducaoPercentual;
    private String tipoResiduo;

    // Construtores, getters e setters
    public Relatorio() {}

    public Relatorio(int id, Date dataInicio, Date dataFim, double totalResiduos, double reducaoPercentual, String tipoResiduo) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.totalResiduos = totalResiduos;
        this.reducaoPercentual = reducaoPercentual;
        this.tipoResiduo = tipoResiduo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date getDataFim() { return dataFim; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }

    public double getTotalResiduos() { return totalResiduos; }
    public void setTotalResiduos(double totalResiduos) { this.totalResiduos = totalResiduos; }

    public double getReducaoPercentual() { return reducaoPercentual; }
    public void setReducaoPercentual(double reducaoPercentual) { this.reducaoPercentual = reducaoPercentual; }

    public String getTipoResiduo() { return tipoResiduo; }
    public void setTipoResiduo(String tipoResiduo) { this.tipoResiduo = tipoResiduo; }

}
