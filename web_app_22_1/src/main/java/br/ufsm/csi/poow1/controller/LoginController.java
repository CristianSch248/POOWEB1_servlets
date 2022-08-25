package br.ufsm.csi.poow1.controller;

import br.ufsm.csi.poow1.model.Usuario;
import br.ufsm.csi.poow1.service.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[login no sistema]");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[Requisição do cliente com POST]");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        RequestDispatcher rd;

        Usuario usuario = new UsuarioService().autenticado(email, senha);
        System.out.println(usuario);

        if(usuario != null){
            HttpSession sessao = req.getSession();
            sessao.setAttribute("usuario_logado", usuario);
            rd = req.getRequestDispatcher("/WEB-INF/home/dashboard.jsp");
        }else{
            req.setAttribute("erro", "EMAIL OU SENHA INCORRETOS");
            rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
        }
        rd.forward(req, resp);
    }
}
