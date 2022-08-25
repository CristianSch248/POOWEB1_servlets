package br.ufsm.csi.poow1.model;

public class Usuario {
    int idUsuario;
    String emailUsuario;
    String senha;
    Hospital idHospital;
    Permissao idPermissao;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Hospital getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Hospital idHospital) {
        this.idHospital = idHospital;
    }

    public Permissao getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Permissao idPermissao) {
        this.idPermissao = idPermissao;
    }
}
