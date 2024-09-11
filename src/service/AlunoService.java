package service;

import AO.AlunoAO;
import model.Aluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AlunoService {
    private AlunoAO alunoAO;

    public AlunoService() {
        try {
            String url = "jdbc:mysql://localhost:3306/creche";
            String user = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, user, password);
            alunoAO = new AlunoAO(connection);
            AlunoAO.createTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAluno(Aluno aluno) throws SQLException {
        alunoAO.insert(aluno);
    }

    public List<Aluno> getAllAlunos() throws SQLException {
        return alunoAO.findAll();
    }

    public Aluno getAlunoById(Long id) throws SQLException {
        return alunoAO.findById(id);
    }

    public void deleteAluno(Long id) throws SQLException {
        alunoAO.delete(id);
    }
}
