 package br.edu.ifce.meuprimeirospringboot.models;

import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_aluno"))
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_turma"))
    private Turma turma;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_matricula", nullable = false)
    private StatusMatricula statusMatricula;

    @Column(name = "semestre", nullable = false)
    private String semestre;

    @Column(name = "data_cancelamento")
    private LocalDate dataCancelamento;

    // Construtores
    public Matricula() {}

    public Matricula(Aluno aluno, Turma turma, LocalDate dataMatricula, StatusMatricula statusMatricula, String semestre, LocalDate dataCancelamento) {
        this.aluno = aluno;
        this.turma = turma;
        this.dataMatricula = dataMatricula;
        this.statusMatricula = statusMatricula;
        this.semestre = semestre;
        this.dataCancelamento = dataCancelamento;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public StatusMatricula getStatusMatricula() { return statusMatricula; }
    public void setStatusMatricula(StatusMatricula statusMatricula) { this.statusMatricula = statusMatricula; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
    public LocalDate getDataCancelamento() { return dataCancelamento; }
    public void setDataCancelamento(LocalDate dataCancelamento) { this.dataCancelamento = dataCancelamento; }
}
