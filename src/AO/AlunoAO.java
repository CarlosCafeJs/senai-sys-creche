package AO;

import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoAO {
    private Connection connection;

    public AlunoAO(Connection connection) {
        this.connection = connection;
    }

    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS aluno (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100), idade INT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, idade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getIdade());
            pstmt.executeUpdate();
        }
    }

    public List<Aluno> findAll() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                Aluno aluno = new Aluno(nome, idade, id);
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public Aluno findById(Long id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    Aluno aluno = new Aluno(nome, idade, id);
                    return aluno;
                }
            }
        }
        return null;
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}
