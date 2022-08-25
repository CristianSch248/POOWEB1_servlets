package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Atendente;

import java.sql.*;
import java.util.ArrayList;

public class AtendenteDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrarAtendente(Atendente atendente){
        System.out.println("vou cadastrar novo Atendente");

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO(nome_atendente, id_usuario) " +
                    "VALUES (?, ?); ";

            this.preparedStatement = connection.prepareStatement(this.sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, atendente.getNomeAtendente());
            this.preparedStatement.setInt(2, atendente.getIdUsuario().getIdUsuario());

            System.out.println(atendente.getNomeAtendente());
            System.out.println(atendente.getIdUsuario());

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

    public String editar(Atendente atendente){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);
            this.sql = "UPDATE atendente SET nome_atendente = ?, id_usuario = ?" +
                    "where id_atendente = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, atendente.getNomeAtendente());
            this.preparedStatement.setInt(2, atendente.getIdUsuario().getIdUsuario());
            this.preparedStatement.executeUpdate();

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = "OK";
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "OK";
        }
        return "";
    }

    public String excluir(Atendente atendente) {
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM atentente where id_atendente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, atendente.getIdAtendete());
            this.preparedStatement.executeUpdate();

            System.out.println(atendente.getIdAtendete());

            if (this.preparedStatement.getUpdateCount() > 0) {
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
