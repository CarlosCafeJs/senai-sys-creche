package AO;

import model.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaAO {
  private Connection connection;

  public TurmaAO(Connection connection) {
    this.connection = connection;
  }

  public static void createTable(Connection connection) throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS turma (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100))";
    try (Statement stmt = connection.createStatement()) {
      stmt.execute(sql);
    }
  }

  public void insert(Turma turma) throws SQLException {
    String sql = "INSERT INTO turma (nome) VALUES (?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, turma.getNome());
      pstmt.executeUpdate();
    }
  }

  public List<Turma> findAll() throws SQLException {
    List<Turma> turmas = new ArrayList<>();
    String sql = "SELECT * FROM turma";
    try (Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        Turma turma = new Turma();
        turma.setId(rs.getInt("id"));
        turma.setNome(rs.getString("nome"));
        turmas.add(turma);
      }
    }
    return turmas;
  }

  public Turma findById(int id) throws SQLException {
    String sql = "SELECT * FROM turma WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Turma turma = new Turma();
          turma.setId(rs.getInt("id"));
          turma.setNome(rs.getString("nome"));
          return turma;
        }
      }
    }
    return null;
  }

  public void delete(int id) throws SQLException {
    String sql = "DELETE FROM turma WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    }
  }
}
