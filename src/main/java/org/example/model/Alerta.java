package org.example.model;

import java.util.Date;

public class Alerta {
        private int id;
        private String tipo;
        private String mensagem;
        private String dataColeta;
        private String bairro;
        private boolean ativo;

        // Construtores, getters e setters
        public Alerta() {}

        public Alerta(int id, String tipo, String mensagem, String dataColeta, String bairro, boolean ativo) {
            this.id = id;
            this.tipo = tipo;
            this.mensagem = mensagem;
            this.dataColeta = dataColeta;
            this.bairro = bairro;
            this.ativo = ativo;
        }

        // Getters e Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }

        public String getMensagem() { return mensagem; }
        public void setMensagem(String mensagem) { this.mensagem = mensagem; }

        public String getDataColeta() { return dataColeta; }
        public void setDataColeta(String dataColeta) { this.dataColeta = dataColeta; }

        public String getBairro() { return bairro; }
        public void setBairro(String bairro) { this.bairro = bairro; }

        public boolean getAtivo() { return ativo; }
        public void setAtivo(boolean ativo) { this.ativo = ativo; }
    }


