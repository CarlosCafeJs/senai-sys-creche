import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.Professor;
import model.Turma;
import service.AlunoService;
import service.ProfessorService;
import service.TurmaService;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();
        ProfessorService professorService = new ProfessorService();
        TurmaService turmaService = new TurmaService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Professores");
            System.out.println("3. Gerenciar Turmas");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    gerenciarAlunos(scanner, alunoService);
                    break;
                case 2:
                    gerenciarProfessores(scanner, professorService);
                    break;
                case 3:
                    gerenciarTurmas(scanner, turmaService);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void gerenciarAlunos(Scanner scanner, AlunoService alunoService) {
        while (true) {
            System.out.println("Gerenciar Alunos:");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Remover Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome do aluno: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade do aluno: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    Aluno novoAluno = new Aluno();
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
                    System.out.print("ID do aluno para remover: ");
                    Long idRemover = scanner.nextLong();
                    scanner.nextLine(); // Consumir a nova linha

                    try {
                        alunoService.deleteAluno(idRemover);
                        System.out.println("Aluno removido com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        List<Aluno> alunos = alunoService.getAllAlunos();
                        for (Aluno a : alunos) {
                            System.out.println("Aluno: " + a.getNome() + ", Idade: " + a.getIdade());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void gerenciarProfessores(Scanner scanner, ProfessorService professorService) {
        while (true) {
            System.out.println("Gerenciar Professores:");
            System.out.println("1. Adicionar Professor");
            System.out.println("2. Remover Professor");
            System.out.println("3. Listar Professores");
            System.out.println("4. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome do professor: ");
                    String nome = scanner.nextLine();
                    System.out.print("Matéria do professor: ");
                    String materia = scanner.nextLine();

                    Professor novoProfessor = new Professor();
                    novoProfessor.setNome(nome);
                    novoProfessor.setMateria(materia);

                    try {
                        professorService.addProfessor(novoProfessor);
                        System.out.println("Professor adicionado com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.print("ID do professor para remover: ");
                    Long idRemover = scanner.nextLong();
                    scanner.nextLine(); // Consumir a nova linha

                    try {
                        professorService.deleteProfessor(idRemover);
                        System.out.println("Professor removido com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        List<Professor> professores = professorService.getAllProfessores();
                        for (Professor p : professores) {
                            System.out.println("Professor: " + p.getNome() + ", Matéria: " + p.getMateria());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void gerenciarTurmas(Scanner scanner, TurmaService turmaService) {
        while (true) {
            System.out.println("Gerenciar Turmas:");
            System.out.println("1. Adicionar Turma");
            System.out.println("2. Remover Turma");
            System.out.println("3. Listar Turmas");
            System.out.println("4. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome da turma: ");
                    String nome = scanner.nextLine();

                    Turma novaTurma = new Turma();
                    novaTurma.setNome(nome);

                    try {
                        turmaService.addTurma(novaTurma);
                        System.out.println("Turma adicionada com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.print("ID da turma para remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    try {
                        turmaService.deleteTurma(idRemover);
                        System.out.println("Turma removida com sucesso.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        List<Turma> turmas = turmaService.getAllTurmas();
                        for (Turma t : turmas) {
                            System.out.println("Turma: " + t.getNome());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
