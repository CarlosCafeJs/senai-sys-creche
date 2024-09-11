package service;

import AO.TurmaAO;
import model.Turma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TurmaService {
  private TurmaAO turmaAO;

  public TurmaService() {
    try {
      String url = "jdbc:mysql://localhost:3306/creche";
      String user = "root";
      String password = "1234";
      Connection connection = DriverManager.getConnection(url, user, password);
      turmaAO = new TurmaAO(connection);
      TurmaAO.createTable(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addTurma(Turma turma) throws SQLException {
    turmaAO.insert(turma);
  }

  public List<Turma> getAllTurmas() throws SQLException {
    return turmaAO.findAll();
  }

  public Turma getTurmaById(int id) throws SQLException {
    return turmaAO.findById(id);
  }

  public void deleteTurma(int id) throws SQLException {
    turmaAO.delete(id);
  }
}
