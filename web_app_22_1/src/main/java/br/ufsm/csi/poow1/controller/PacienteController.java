package br.ufsm.csi.poow1.controller;

import br.ufsm.csi.poow1.dao.PacienteDAO;
import br.ufsm.csi.poow1.model.Paciente;
import br.ufsm.csi.poow1.service.PacienteService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("paciente-controller")
public class PacienteController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PacienteDAO dao = new PacienteDAO();
        String retorno = "";
        String opcao = req.getParameter("opcao");
        String caminho = "";
        String mensagem = ""; // resultado do CRUD

        switch (opcao) {
            case "excluir": {
                caminho = "pacientes";
                System.out.println("EXCLUIR");
                int id = Integer.parseInt(req.getParameter("idPaciente"));
                retorno = dao.excluirPaciente(id);

                if (retorno.equals("OK")) {
                    mensagem = "Paciente excluído";
                } else if (retorno.equals("ERRO")) {
                    mensagem = "Não foi possível excluir o paciente";
                }

                req.setAttribute("pacientes", new PacienteService().listar());
                break;
            }
            case "editar": {
                caminho = "editar-controller";
                int id = Integer.parseInt(req.getParameter("idPaciente"));
                Paciente paciente = new PacienteDAO().getPacienteUnico(id);
                req.setAttribute("p", paciente);
                break;
            }
            case "atualizar": {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(Integer.parseInt(req.getParameter("idPaciente")));
                paciente.setNomePaciente(req.getParameter("nome"));
                paciente.setIdade(Integer.parseInt(req.getParameter("idade")));
                paciente.setSexo(req.getParameter("sexo"));
                paciente.setTelefone(req.getParameter("telefone"));
                paciente.setCPF(req.getParameter("cpf"));

                retorno = new PacienteDAO().editarPaciente(paciente);

                if (retorno.equals("OK")) {
                    mensagem = "As informações do paciente foram atualizas";
                } else if (retorno.equals("ERRO")) {
                    mensagem = "Não foi possível editar o paciente";
                }

                req.setAttribute("pacientes", new PacienteService().listar());
                break;
            }
            case "cadastrar": {
//                String nome = req.getParameter("nome");
//                int idade = Integer.parseInt(req.getParameter("idade"));
//                String sexo = req.getParameter("sexo");
//                String telefone = req.getParameter("telefone");
//                String cpf = req.getParameter("cpf");

                Paciente paciente = new Paciente();
                paciente.setNomePaciente(req.getParameter("nome"));
                paciente.setIdade(Integer.parseInt(req.getParameter("idade")));
                paciente.setSexo(req.getParameter("sexo"));
                paciente.setTelefone(req.getParameter("telefone"));
                paciente.setCPF(req.getParameter("cpf"));

                if (new PacienteService().cadastro(paciente) != null) {
                    mensagem = "Paciente cadastrado com sucesso";
                } else {
                    mensagem = "Não foi possível cadastrar o paciente";
                }

                req.setAttribute("pacientes", new PacienteService().listar());
                break;
            }

            default: {
                req.setAttribute("pacientes", new PacienteService().listar());
                break;
            }
        }

        req.setAttribute("mensagem", mensagem);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/home/paciente.jsp");
//        RequestDispatcher rd = req.getRequestDispatcher(caminho);
        rd.forward(req, resp);


//        if(opcao.equals("excluir")){
//            caminho = "pacientes";
//            System.out.println("EXCLUIRA");
//            int id = Integer.parseInt(req.getParameter("idPaciente"));
//            Paciente paciente = new PacienteDAO().getPacienteUnico(id);
//            retorno = dao.excluirPaciente(paciente);
//
//            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/home/paciente.jsp");
//            rd.forward(req, resp);
//        } else if (opcao.equals("editar")) {
//            caminho = "editar-controller";
//            int id = Integer.parseInt(req.getParameter("idPaciente"));
//            Paciente paciente = new PacienteDAO().getPacienteUnico(id);
//            req.setAttribute("paciente", paciente);
//        } else{
//            String nome = req.getParameter("nome");
//            int idade = Integer.parseInt(req.getParameter("idade"));
//            String sexo = req.getParameter("sexo");
//            String telefone = req.getParameter("telefone");
//            String cpf = req.getParameter("cpf");
//
//            Paciente paciente = new Paciente();
//            paciente.setNomePaciente(nome);
//            paciente.setIdade(idade);
//            paciente.setSexo(sexo);
//            paciente.setTelefone(telefone);
//            paciente.setCPF(cpf);
//
//            new PacienteService().cadastro(paciente);
//        }
    }
}
