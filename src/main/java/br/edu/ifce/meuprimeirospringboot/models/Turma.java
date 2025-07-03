package br.edu.ifce.meuprimeirospringboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id", nullable = false, foreignKey = @ForeignKey(name = "fk_turma_disciplina"))
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_turma_professor"))
	private Professor professor;
	
	@Column(name = "semestre", nullable = false)
	private String semestre;
	
	@Column(name = "codigo_turma", nullable = false, unique = true)
	private String codigoTurma;
	
	@Column(name = "vagas_totais", nullable = false)
	private Integer vagasTotais;
	
	@Column(name = "sala", nullable = false)
	private String sala;
	
	// Construtores
	public Turma() {}
	
	public Turma(Disciplina disciplina, Professor professor, String semestre, 
	             String codigoTurma, Integer vagasTotais, String sala) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.semestre = semestre;
		this.codigoTurma = codigoTurma;
		this.vagasTotais = vagasTotais;
		this.sala = sala;
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public String getSemestre() {
		return semestre;
	}
	
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	public String getCodigoTurma() {
		return codigoTurma;
	}
	
	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
	
	public Integer getVagasTotais() {
		return vagasTotais;
	}
	
	public void setVagasTotais(Integer vagasTotais) {
		this.vagasTotais = vagasTotais;
	}
	
	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	@Override
	public String toString() {
		return "Turma [id=" + id + ", disciplina=" + disciplina + ", professor=" + professor + 
			   ", semestre=" + semestre + ", codigoTurma=" + codigoTurma + 
			   ", vagasTotais=" + vagasTotais + ", sala=" + sala + "]";
	}
} 