package br.ufsm.csi.poow1.model;

public class Quarto {
    int IdQuarto;
    Hospital idHospital;

    public Quarto() {
    }

    public int getIdQuarto() {
        return IdQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        IdQuarto = idQuarto;
    }

    public Hospital getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Hospital idHospital) {
        this.idHospital = idHospital;
    }
}
