package br.ufsm.csi.poow1.model;

import java.sql.Date;

public class Atendimento {
    int idAtendimento;
    Date dataEntrada;
    Date dataSaida;
    String caso;
    boolean atendimentoStatus;
    Paciente idPaciente;
    Medico idMedico;

    public Atendimento() {
    }

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public boolean isAtendimentoStatus() {
        return atendimentoStatus;
    }

    public void setAtendimentoStatus(boolean atendimentoStatus) {
        this.atendimentoStatus = atendimentoStatus;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }
}
