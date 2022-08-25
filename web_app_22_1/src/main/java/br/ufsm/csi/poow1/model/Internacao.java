package br.ufsm.csi.poow1.model;

import java.sql.Date;

public class Internacao {
    int idInternacao;
    Date dataEntrada;
    Date dataSaida;
    boolean internacaoStatus;
    Leito idLeito;
    Paciente idPaciente;

    public int getIdInternacao() {
        return idInternacao;
    }

    public void setIdInternacao(int idInternacao) {
        this.idInternacao = idInternacao;
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

    public boolean isInternacaoStatus() {
        return internacaoStatus;
    }

    public void setInternacaoStatus(boolean internacaoStatus) {
        this.internacaoStatus = internacaoStatus;
    }

    public Leito getIdLeito() {
        return idLeito;
    }

    public void setIdLeito(Leito idLeito) {
        this.idLeito = idLeito;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setIdPaciente(int id_paciente) {
    }

    public void setIdLeito(int id_leito) {
    }

    public void getDataEntrada(Date data_entrada) {
    }

}
