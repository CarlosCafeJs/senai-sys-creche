package model;

public class Professor extends Pessoas{
    private Long id;
    private String materia;

    public Professor(String nome, int idade, Long id, String materia) {
        super(nome, idade);
        this.id = id;
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
