package service;

import AO.ProfessorAO;
import model.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProfessorService {
  private ProfessorAO professorAO;

  public ProfessorService() {
    try {
      String url = "jdbc:mysql://localhost:3306/creche";
      String user = "root";
      String password = "1234";
      Connection connection = DriverManager.getConnection(url, user, password);
      professorAO = new ProfessorAO(connection);
      ProfessorAO.createTable(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addProfessor(Professor professor) throws SQLException {
    professorAO.insert(professor);
  }

  public List<Professor> getAllProfessores() throws SQLException {
    return professorAO.findAll();
  }

  public Professor getProfessorById(Long id) throws SQLException {
    return professorAO.findById(id);
  }

  public void deleteProfessor(Long id) throws SQLException {
    professorAO.delete(id);
  }
}
