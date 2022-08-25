package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Permissao;

import java.sql.*;
import java.util.ArrayList;

public class PermissaoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Permissao> getPermissoes(){
        ArrayList<Permissao> permissoes = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM permissao; ";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Permissao permissao = new Permissao();
                permissao.setIdPermissao(this.resultSet.getInt("id_permissao"));
                permissao.setNomePermissao(this.resultSet.getString("nome_permissao"));

                System.out.println(permissao.getIdPermissao());
                System.out.println(permissao.getNomePermissao());

                permissoes.add(permissao);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return permissoes;
    }

    public String cadastraPermissao(Permissao permissao){
        System.out.println("Vou cadastrar nova permissão");

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "    INSERT INTO permissao (nome_permissao) values (?); ";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, permissao.getNomePermissao());

            System.out.println(permissao.getNomePermissao());

            this.preparedStatement.execute();
            this.resultSet = preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(1) > 0) {
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

    public String editarPermissao(Permissao permissao) {
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "UPDATE permissao SET nome_permissao = ?" +
                    "WHERE id_permissao = ?; ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, permissao.getNomePermissao());
            this.preparedStatement.setInt(2, permissao.getIdPermissao());

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

    public String excluirPermissao(Permissao permissao){
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM permissao WHERE id_permissao = ?; ";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, permissao.getIdPermissao());
            this.preparedStatement.executeUpdate();

            System.out.println(permissao.getIdPermissao());

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