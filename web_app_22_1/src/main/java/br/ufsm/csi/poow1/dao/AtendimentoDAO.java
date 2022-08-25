package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Atendimento;
import br.ufsm.csi.poow1.model.Medico;
import br.ufsm.csi.poow1.model.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class AtendimentoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Atendimento> getAtendiento(){
        ArrayList<Atendimento> atendimentos = new ArrayList<>();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT nome_paciente, caso, data_entrada, nome_medico," +
                    "FROM paciente, atendimento, medico WHERE paciente.id_paciente = atendimento.id_paciente," +
                    "AND atendimento.id_medico = medico.id_medico," +
                    "AND atendimento.atendimento_status = 'TRUE'; ";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Paciente paciente = new Paciente();
                paciente.setNomePaciente(this.resultSet.getString("nome_paciente"));

                Atendimento atendimento = new Atendimento();
                atendimento.setCaso(this.resultSet.getString("caso"));
                atendimento.setDataEntrada(this.resultSet.getDate("data_entrada"));

                Medico medico = new Medico();
                medico.setNomeMedico(this.resultSet.getString("nome_medico"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return atendimentos;
    }

    public String novoAtendiemto(Atendimento atendimento){
        System.out.println("criando um novo atendimento");

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO atendimento " +
                    "(id_paciente, id_medico, data_entrada, data_saida, caso, atendimento_status)" +
                    "values(?, ?, CURRENT_DATE, CURRENT_DATE, ?, ?); ";

            this.preparedStatement = connection.prepareStatement(this.sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, atendimento.getIdPaciente().getIdPaciente());
            this.preparedStatement.setInt(2, atendimento.getIdMedico().getIdMedico());
            this.preparedStatement.setString(5, atendimento.getCaso());
            this.preparedStatement.setBoolean(6, atendimento.isAtendimentoStatus());

            System.out.println(atendimento.getCaso());
            System.out.println(atendimento.isAtendimentoStatus());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "okk";
                connection.commit();
                System.out.println(this.status);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERROR";
            System.out.println(this.status);
        }
        return this.status;
    }

    public String editar(Atendimento atendimento){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "UPDATE atendimento SET id_paciente = ?, id_medico = ?, " +
                    "data_entrada = ?, data_saida = ?, caso = ? " +
                    "WHERE id_atendimento = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, atendimento.getIdPaciente().getIdPaciente());
            this.preparedStatement.setInt(2, atendimento.getIdMedico().getIdMedico());
            this.preparedStatement.setDate(3, atendimento.getDataEntrada());
            this.preparedStatement.setDate(4, atendimento.getDataSaida());
            this.preparedStatement.setString(5, atendimento.getCaso());

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = "OK";
                connection.commit();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERROR";
        }
        return "";
    }

    public String excluirAtendimeto(Atendimento atendimento){
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM atendimeto WHERE id_atendimetos = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, atendimento.getIdAtendimento());
            this.preparedStatement.executeUpdate();

            System.out.println(atendimento.getIdAtendimento());

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = "OKk";
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
