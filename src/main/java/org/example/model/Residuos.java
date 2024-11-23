package org.example.model;

public class Residuos {
        private int id;
        private String tipo;
        private String descricao;
        private String instrucoesDescarte;
        private boolean reciclavel;

        // Construtor vazio
        public Residuos() {}

        // Construtor com parâmetros
        public Residuos(int id, String tipo, String descricao, String instrucoesDescarte, boolean reciclavel) {
            this.id = id;
            this.tipo = tipo;
            this.descricao = descricao;
            this.instrucoesDescarte = instrucoesDescarte;
            this.reciclavel = reciclavel;

        }

        // Getters e Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }

        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }

        public String getInstrucoesDescarte() { return instrucoesDescarte; }
        public void setInstrucoesDescarte(String instrucoesDescarte) { this.instrucoesDescarte = instrucoesDescarte; }

        public boolean isReciclavel() { return reciclavel; }
        public void setReciclavel(boolean reciclavel) { this.reciclavel = reciclavel; }
    }


