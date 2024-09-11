import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Alunos;
import service.AlunoService;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Remover Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Modificar Aluno");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    // Adicionar Aluno
                    System.out.print("Nome do aluno: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade do aluno: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    Alunos novoAluno = new Alunos();
                    novoAluno.setNome(nome);
                    novoAluno.setIdade(idade);

                    try {
                        alunoService.addAluno(novoAluno);
                        System.out.println("Aluno adicionado com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // Remover Aluno
                    System.out.print("ID do aluno para remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    try {
                        alunoService.deleteAluno(idRemover);
                        System.out.println("Aluno removido com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    // Listar Alunos
                    try {
                        List<Alunos> alunos = alunoService.getAllAlunos();
                        for (Alunos a : alunos) {
                            System.out.println("Aluno: " + a.getNome() + ", Idade: " + a.getIdade());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    // Modificar Aluno
                    System.out.print("ID do aluno para modificar: ");
                    int idModificar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    System.out.print("Novo nome do aluno: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova idade do aluno: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    try {
                        Alunos alunoParaModificar = alunoService.getAlunoById(idModificar);
                        if (alunoParaModificar != null) {
                            alunoParaModificar.setNome(novoNome);
                            alunoParaModificar.setIdade(novaIdade);
                            alunoService.addAluno(alunoParaModificar);
                            System.out.println("Aluno modificado com sucesso.");
                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
