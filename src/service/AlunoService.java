package service;

import AO.AlunoAO;
import model.Alunos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AlunoService {
    private AlunoAO alunoAO;

    public AlunoService() {
        try {

            String url = "jdbc:mysql://172.19.0.2:3306/creche";
            String user = "root";
            String password = "1234";

            Connection connection = DriverManager.getConnection(url, user, password);

            alunoAO = new AlunoAO(connection);

            AlunoAO.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAluno(Alunos aluno) throws SQLException {
        alunoAO.insert(aluno);
    }

    public List<Alunos> getAllAlunos() throws SQLException {
        return alunoAO.findAll();
    }

    public Alunos getAlunoById(int id) throws SQLException {
        return alunoAO.findById(id);
    }

    public void deleteAluno(int id) throws SQLException {
        alunoAO.delete(id);
    }
}
