package br.ufsm.csi.poow1.service;

import br.ufsm.csi.poow1.dao.UsuarioDAO;
import br.ufsm.csi.poow1.model.Usuario;

public class UsuarioService {
    private UsuarioDAO dao;

    public Usuario autenticado(String email, String senha){

        dao = new UsuarioDAO();

        Usuario usuario = dao.getUsuario(email, senha);

        try{
            if(usuario.getEmailUsuario().equals(email) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        } catch (Exception e){
            e.printStackTrace();
            //throw new RuntimeException("Usuário não encontrado!", e);
        }
        return null;
    }
}
