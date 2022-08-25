package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Internacao;

import java.sql.*;
import java.util.ArrayList;

public class InternacaoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Internacao> getInternacao(){
        ArrayList<Internacao> internacoes = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM internacao" +
                    "WHERE interna_status = true; ";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Internacao internacao = new Internacao();
                internacao.setIdInternacao(this.resultSet.getInt("id_internação"));
                internacao.setIdPaciente(this.resultSet.getInt("id_paciente"));
                internacao.setIdLeito(this.resultSet.getInt("id_leito"));
                internacao.setDataEntrada(this.resultSet.getDate("data_entrada"));
                internacao.setDataSaida(this.resultSet.getDate("data_saida"));
                internacao.setInternacaoStatus(this.resultSet.getBoolean("interna_status"));

                System.out.println(internacao.getIdInternacao());
                System.out.println(internacao.getIdPaciente());
                System.out.println(internacao.getIdLeito());
                System.out.println(internacao.getDataEntrada());
                System.out.println(internacao.getDataSaida());
                System.out.println(internacao.isInternacaoStatus());

                internacoes.add(internacao);
            }
        }catch (SQLException e) {
        e.printStackTrace();
        }
        return internacoes;
    }

    public String novaInternacao(Internacao internacao){
        System.out.println("Vou cadastrar");
        try(Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO internacao (id_usuario, id_leito, interna_status, data_estrada, data_saida) " +
                    "VALUES (?, ?, ?, CURRENT_DATE, CURRENT_DATE); ";

            this.preparedStatement = connection.prepareStatement(this.sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, internacao.getIdPaciente().getIdPaciente());
            this.preparedStatement.setInt(2, internacao.getIdLeito().getIdLeito());
            this.preparedStatement.setBoolean(3, internacao.isInternacaoStatus());

            System.out.println(internacao.getIdPaciente());
            System.out.println(internacao.getIdLeito());
            System.out.println(internacao.isInternacaoStatus());
            System.out.println(internacao.getDataEntrada());
            System.out.println(internacao.getDataSaida());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(1) > 0) {
                this.status = "OKKey";
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

    public String editarInternacao(Internacao internacao) {
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "UPDATE internacao " +
                    "SET id_paciente = ?," +
                    "id_leito = ?, " +
                    "interna_status = ?," +
                    "data_entrada = ?," +
                    "data_saida = ?, " +
                    "WHERE id_internacao = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, internacao.getIdPaciente().getIdPaciente());
            this.preparedStatement.setInt(2, internacao.getIdLeito().getIdLeito());
            this.preparedStatement.setBoolean(3, internacao.isInternacaoStatus());
            this.preparedStatement.setDate(4, internacao.getDataEntrada());
            this.preparedStatement.setDate(5, internacao.getDataSaida());

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

    public String excluirInternacao(Internacao internacao){
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM internacao WHERE id_internacao = ?; ";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, internacao.getIdInternacao());
            this.preparedStatement.executeUpdate();

            System.out.println(internacao.getIdInternacao());

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
