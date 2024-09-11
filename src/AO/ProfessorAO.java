package AO;

import model.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorAO {
  private Connection connection;

  public ProfessorAO(Connection connection) {
    this.connection = connection;
  }

  public static void createTable(Connection connection) throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS professor (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100), materia VARCHAR(100))";
    try (Statement stmt = connection.createStatement()) {
      stmt.execute(sql);
    }
  }

  public void insert(Professor professor) throws SQLException {
    String sql = "INSERT INTO professor (nome, materia) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, professor.getNome());
      pstmt.setString(2, professor.getMateria());
      pstmt.executeUpdate();
    }
  }

  public List<Professor> findAll() throws SQLException {
    List<Professor> professores = new ArrayList<>();
    String sql = "SELECT * FROM professor";
    try (Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        Professor professor = new Professor();
        professor.setId(rs.getLong("id"));
        professor.setNome(rs.getString("nome"));
        professor.setMateria(rs.getString("materia"));
        professores.add(professor);
      }
    }
    return professores;
  }

  public Professor findById(Long id) throws SQLException {
    String sql = "SELECT * FROM professor WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Professor professor = new Professor();
          professor.setId(rs.getLong("id"));
          professor.setNome(rs.getString("nome"));
          professor.setMateria(rs.getString("materia"));
          return professor;
        }
      }
    }
    return null;
  }

  public void delete(Long id) throws SQLException {
    String sql = "DELETE FROM professor WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, id);
      pstmt.executeUpdate();
    }
  }
}
