package model;

public abstract class Professores {

    private Long id;
    private String nome;
    private String materia;

    public Professores(Long id, String nome, String materia) {
        this.id = id;
        this.nome = nome;
        this.materia = materia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }




}

