package br.ufsm.csi.poow1.model;

public class Medico {
    int idMedico;
    String nomeMedico;
    String especialidade;
    Hospital idHpspital;
    Usuario idUsuario;

    public Medico() {
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Hospital getIdHpspital() {
        return idHpspital;
    }

    public void setIdHpspital(Hospital idHpspital) {
        this.idHpspital = idHpspital;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
