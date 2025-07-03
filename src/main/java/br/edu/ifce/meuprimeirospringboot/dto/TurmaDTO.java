package br.edu.ifce.meuprimeirospringboot.dto;

public class TurmaDTO {
	private Long id;
	private Long disciplinaId;
	private Long professorId;
	private String semestre;
	private String codigoTurma;
	private Integer vagasTotais;
	private String sala;
	
	// Campos adicionais para exibição (não persistidos)
	private String nomeDisciplina;
	private String nomeProfessor;
	
	// Construtores
	public TurmaDTO() {}
	
	public TurmaDTO(Long id, Long disciplinaId, Long professorId, String semestre, 
	                String codigoTurma, Integer vagasTotais, String sala) {
		this.id = id;
		this.disciplinaId = disciplinaId;
		this.professorId = professorId;
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
	
	public Long getDisciplinaId() {
		return disciplinaId;
	}
	
	public void setDisciplinaId(Long disciplinaId) {
		this.disciplinaId = disciplinaId;
	}
	
	public Long getProfessorId() {
		return professorId;
	}
	
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
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
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	
	@Override
	public String toString() {
		return "TurmaDTO [id=" + id + ", disciplinaId=" + disciplinaId + ", professorId=" + professorId + 
			   ", semestre=" + semestre + ", codigoTurma=" + codigoTurma + 
			   ", vagasTotais=" + vagasTotais + ", sala=" + sala + 
			   ", nomeDisciplina=" + nomeDisciplina + ", nomeProfessor=" + nomeProfessor + "]";
	}
} 