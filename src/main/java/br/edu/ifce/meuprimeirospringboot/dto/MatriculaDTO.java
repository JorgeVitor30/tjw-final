package br.edu.ifce.meuprimeirospringboot.dto;

import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import java.time.LocalDate;

public class MatriculaDTO {
    private Long id;
    private Long alunoId;
    private Long turmaId;
    private LocalDate dataMatricula;
    private StatusMatricula statusMatricula;
    private String semestre;
    private LocalDate dataCancelamento;

    // Construtores
    public MatriculaDTO() {}

    public MatriculaDTO(Long id, Long alunoId, Long turmaId, LocalDate dataMatricula, StatusMatricula statusMatricula, String semestre, LocalDate dataCancelamento) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.dataMatricula = dataMatricula;
        this.statusMatricula = statusMatricula;
        this.semestre = semestre;
        this.dataCancelamento = dataCancelamento;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public Long getTurmaId() { return turmaId; }
    public void setTurmaId(Long turmaId) { this.turmaId = turmaId; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public StatusMatricula getStatusMatricula() { return statusMatricula; }
    public void setStatusMatricula(StatusMatricula statusMatricula) { this.statusMatricula = statusMatricula; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
    public LocalDate getDataCancelamento() { return dataCancelamento; }
    public void setDataCancelamento(LocalDate dataCancelamento) { this.dataCancelamento = dataCancelamento; }
} 