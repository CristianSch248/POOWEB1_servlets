package br.ufsm.csi.poow1.model;

public class Atendente {
    int idAtendete;
    String nomeAtendente;
    Usuario idUsuario;

    public Atendente() {
    }

    public int getIdAtendete() {
        return idAtendete;
    }

    public void setIdAtendete(int idAtendete) {
        this.idAtendete = idAtendete;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
