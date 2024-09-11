package model;

public class Professor extends Pessoas {
    private Long id;
    private String materia;

    public Professor(String nome, int idade, String materia) {
        super(nome, idade);

        this.materia = materia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
