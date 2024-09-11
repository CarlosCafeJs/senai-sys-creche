package model;

public class Aluno extends Pessoas {
    private Long id;

    public Aluno(String nome, int idade) {
        super(nome, idade);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
