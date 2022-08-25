package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Permissao;
import br.ufsm.csi.poow1.model.Usuario;

import java.sql.*;

public class UsuarioDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public Usuario getUsuario(String email, String senha){
        Usuario usuario = null;

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT U.id_usuario, U.email, U.senha, U.id_permissao " +
                    "FROM usuario U, permissao P WHERE email = ? " +
                    "AND senha = ? AND U.id_permissao = P.id_permissao; ";

            preparedStatement = connection.prepareStatement(this.sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario((resultSet.getInt("id_usuario")));
                usuario.setEmailUsuario(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                /*usuario.setPermissao(new Permissao(
                        resultSet.getInt("id_permissao"),
                        resultSet.getString("nome_permissao")*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public String cadastraUsuario(Usuario usuario){

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT  INTO usuario (email, senha, id_hospital, id_permissao)" +
                    "values (?, ?, ?, ?); ";

            this.preparedStatement=connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, usuario.getEmailUsuario());
            this.preparedStatement.setString(2, usuario.getSenha());
            this.preparedStatement.setInt(3, usuario.getIdHospital().getIdHospital());
            this.preparedStatement.setInt(4, usuario.getIdPermissao().getIdPermissao());

            System.out.println(usuario.getEmailUsuario());
            System.out.println(usuario.getSenha());
            System.out.println(usuario.getIdHospital());
            System.out.println(usuario.getIdPermissao());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OK";
                connection.commit();
                System.out.println(this.status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERROR";
            System.out.println(this.status);
        }
        return this.status;
    }

    public String editarUsuario(Usuario usuario){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "update USUARIO set email = ?, senha = ?, id_hospital = ?, id_permissão = ? " +
                    "where id_usuario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, usuario.getEmailUsuario());
            this.preparedStatement.setString(2, usuario.getSenha());
            this.preparedStatement.setInt(3, usuario.getIdHospital().getIdHospital());
            this.preparedStatement.setInt(4, usuario.getIdPermissao().getIdPermissao());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount() > 0){
                this.status = "OKk";
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "OK";
        }
        return "";
    }

    public String excluirUsuario(Usuario usuario){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM usuario WHERE id_usuario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, usuario.getIdUsuario());
            this.preparedStatement.executeUpdate();

            System.out.println(usuario.getIdUsuario());

            if(this.preparedStatement.getUpdateCount() > 0){
                this.status = "OK";
                System.out.println(this.status);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "OK";
        }
        return "";
    }
}
