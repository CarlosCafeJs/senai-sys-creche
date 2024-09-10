import java.sql.SQLException;
import java.util.List;
import model.Alunos;
import service.AlunoService;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();

        try {

            Alunos aluno = new Alunos();
            aluno.setNome("Carlos");
            aluno.setIdade(8);
            alunoService.addAluno(aluno);

            List<Alunos> alunos = alunoService.getAllAlunos();
            for (Alunos a : alunos) {
                System.out.println("Aluno: " + a.getNome() + ", Idade: " + a.getIdade());
            }

            Alunos alunoEncontrado = alunoService.getAlunoById(1);
            System.out.println(
                    "Aluno encontrado: " + alunoEncontrado.getNome() + ", Idade: " + alunoEncontrado.getIdade());

            alunoService.deleteAluno(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}