package br.ufsm.csi.poow1.controller;

import br.ufsm.csi.poow1.dao.PacienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("controlador")
public class DashboardController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("==========================================");
        System.out.println("user-agent " + req.getHeader("User-Agent"));
        //esse código devolve para o console qual tipo de dispositivo esta fazendo a requisição
        System.out.println("===========================================");

        String uri = "/";

        if(req.getSession().getAttribute("usuario_logado") != null){
            String opcao = req.getParameter("opcao");

            System.out.println("Opcao de navegacao: " + opcao);

            uri = "WEB-INF/home/dashboard.jsp";

            switch (opcao) {
                case "paciente":
                    uri = "WEB-INF/home/paciente.jsp";
                    req.setAttribute("pacientes", new PacienteDAO().getPacientes());
                    break;
                case "atendimento":
                    uri = "WEB-INF/home/atendimento.jsp";
                    break;
                case "internacao":
                    uri = "WEB-INF/home/internacao.jsp";
                    break;
                case "logout":
                    req.getSession().invalidate();
                    uri = "/";
                    break;
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }
}
