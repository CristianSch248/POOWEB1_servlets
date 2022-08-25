package br.ufsm.csi.poow1.service;

import br.ufsm.csi.poow1.dao.PacienteDAO;
import br.ufsm.csi.poow1.model.Paciente;

import java.util.List;

public class PacienteService {
    public String cadastro(Paciente paciente){
        return new PacienteDAO().cadastrarPaciente(paciente);
    }

    public List<Paciente> listar() {
        return new PacienteDAO().getPacientes();
    }
}
