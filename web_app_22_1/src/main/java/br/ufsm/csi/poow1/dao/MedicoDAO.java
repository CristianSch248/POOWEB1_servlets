package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Medico;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Medico> getMedicos(){
        ArrayList<Medico> medicos = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM medico";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Medico medico = new Medico();
                medico.setIdMedico(this.resultSet.getInt("id_medico"));
                medico.setNomeMedico(this.resultSet.getString("nome_medico"));
                medico.setEspecialidade(this.resultSet.getString("especialidade"));
                //medico.setIdHpspital(this.resultSet.getInt("id_hospital"));
                //medico.setIdUsuario(this.resultSet.getInt("id_usuario"));

                System.out.println(medico.getIdMedico());
                System.out.println(medico.getNomeMedico());
                System.out.println(medico.getEspecialidade());

                medicos.add(medico);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    public String cadastraMedico(Medico medico){
        System.out.println("estou cadastrando um medico");
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO medico (nome_medico, especialidade, id_hospital, id_usuario)" +
                    "VALUES(?,?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, medico.getNomeMedico());
            this.preparedStatement.setString(2, medico.getEspecialidade());
            this.preparedStatement.setInt(3, medico.getIdHpspital().getIdHospital());
            this.preparedStatement.setInt(4, medico.getIdUsuario().getIdUsuario());

            System.out.println(medico.getNomeMedico());
            System.out.println(medico.getEspecialidade());
            System.out.println(medico.getIdHpspital());
            System.out.println(medico.getIdUsuario());

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

    public String editaMedico(Medico medico){
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "UPDATE medico SET nome_medico = ?, especialidade = ?, id_hospital = ?, id_usuario = ?" +
                    "WHERE = id_medico = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, medico.getNomeMedico());
            this.preparedStatement.setString(2, medico.getEspecialidade());
            this.preparedStatement.setInt(3, medico.getIdHpspital().getIdHospital());
            this.preparedStatement.setInt(4, medico.getIdUsuario().getIdUsuario());
            this.preparedStatement.executeUpdate();

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

    public String deletaMedico(Medico medico){
        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM medico WHERE id_medico = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, medico.getIdMedico());
            this.preparedStatement.executeUpdate();

            System.out.println(medico.getIdMedico());

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
