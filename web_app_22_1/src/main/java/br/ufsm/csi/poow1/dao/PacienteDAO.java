package br.ufsm.csi.poow1.dao;

import br.ufsm.csi.poow1.model.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Paciente> getPacientes(){
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM paciente ORDER BY nome_paciente";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Paciente paciente = new Paciente();

                paciente.setIdPaciente(this.resultSet.getInt("id_paciente"));
                paciente.setNomePaciente(this.resultSet.getString("nome_paciente"));
                paciente.setIdade(this.resultSet.getInt("idade"));
                paciente.setSexo(this.resultSet.getString("sexo"));
                paciente.setTelefone(this.resultSet.getString("telefone"));
                paciente.setCPF(this.resultSet.getString("cpf"));

                System.out.println(paciente.getIdPaciente());
                System.out.println(paciente.getNomePaciente());
                System.out.println(paciente.getIdade());
                System.out.println(paciente.getSexo());
                System.out.println(paciente.getTelefone());
                System.out.println(paciente.getCPF());

                pacientes.add(paciente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pacientes;
    }

    public Paciente getPaciente(String nomePaciente, String CPF){
        Paciente paciente = null;

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT " +
                    "id_paciente," +
                    "nome_paciente," +
                    "idade," +
                    "sexo," +
                    "telefone," +
                    "cpf," +
                    "FROM paciente WHERE nome_paciente = ?" +
                    "AND cpf = ?; ";

            this.statement = connection.prepareStatement(this.sql);
            preparedStatement.setString(1, nomePaciente);
            preparedStatement.setString(2, CPF);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                paciente = new Paciente();
                paciente.setIdPaciente((resultSet.getInt("id_paciente")));
                paciente.setNomePaciente(resultSet.getString("nome_paciente"));
                paciente.setIdade(resultSet.getInt("idade"));
                paciente.setSexo(resultSet.getString("sexo"));
                paciente.setTelefone(resultSet.getString("telefone"));
                paciente.setCPF(resultSet.getString("cpf"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return paciente;
    }

    public Paciente getPacienteUnico(int idPaciente){
        Paciente paciente = new Paciente();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql="SELECT * FROM paciente " +
                    "WHERE id_paciente = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, idPaciente);
            this.resultSet = this.preparedStatement.executeQuery();

            while(this.resultSet.next()){
                paciente.setIdPaciente((resultSet.getInt("id_paciente")));
                paciente.setNomePaciente(resultSet.getString("nome_paciente"));
                paciente.setIdade(resultSet.getInt("idade"));
                paciente.setSexo(resultSet.getString("sexo"));
                paciente.setTelefone(resultSet.getString("telefone"));
                paciente.setCPF(resultSet.getString("cpf"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    public String cadastrarPaciente(Paciente paciente){
        System.out.println("Cadastrando novo usuario");

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO paciente (nome_paciente, idade, sexo, telefone, cpf)" +
                    "VALUES (?, ?, ?, ?, ?); ";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, paciente.getNomePaciente());
            this.preparedStatement.setInt(2, paciente.getIdade());
            this.preparedStatement.setString(3, paciente.getSexo());
            this.preparedStatement.setString(4, paciente.getTelefone());
            this.preparedStatement.setString(5, paciente.getCPF());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                this.status = "OKK";
                connection.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
            this.status = "ERROR";
        }
        return this.status;
    }

    public String editarPaciente(Paciente paciente){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "UPDATE paciente SET " +
                    "nome_paciente = ?," +
                    "idade = ?, " +
                    "sexo = ?, " +
                    "telefone = ?," +
                    "cpf = ? " +
                    "WHERE id_paciente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, paciente.getNomePaciente());
            this.preparedStatement.setInt(2, paciente.getIdade());
            this.preparedStatement.setString(3, paciente.getSexo());
            this.preparedStatement.setString(4, paciente.getTelefone());
            this.preparedStatement.setString(5, paciente.getCPF());
            this.preparedStatement.setInt(6, paciente.getIdPaciente());
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount() > 0){
                this.status = "OK";
                connection.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
            this.status = "ERRO";
        }
        return this.status;
    }

    public String excluirPaciente(int idPaciente){
        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM paciente WHERE id_paciente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, idPaciente);
            this.preparedStatement.executeUpdate();

            if(this.preparedStatement.getUpdateCount() > 0){
                this.status = "OK";
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }
        return this.status;
    }
}