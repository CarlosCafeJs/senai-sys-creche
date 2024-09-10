package AO;
import model.Alunos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoAO {


    private Connection connection;

    public AlunoAO(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS aluno (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(100), idade INT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(Alunos aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, idade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getIdade());
            pstmt.executeUpdate();
        }
    }

    public List<Alunos> findAll() throws SQLException {
        List<Alunos> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alunos aluno = new Alunos();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public Alunos findById(int id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Alunos aluno = new Alunos();
                    aluno.setId(rs.getLong("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));
                    return aluno;
                }
            }
        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}