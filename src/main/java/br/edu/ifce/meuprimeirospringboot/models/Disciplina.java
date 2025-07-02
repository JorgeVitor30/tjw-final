package br.edu.ifce.meuprimeirospringboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome_disciplina", nullable = false)
	private String nomeDisciplina;
	
	@Column(name = "codigo_disciplina", nullable = false, unique = true)
	private String codigoDisciplina;
	
	@Column(name = "carga_horaria", nullable = false)
	private Integer cargaHoraria;
	
	@Column(name = "ementa", nullable = false, columnDefinition = "TEXT")
	private String ementa;
	
	// Construtores
	public Disciplina() {}
	
	public Disciplina(String nomeDisciplina, String codigoDisciplina, Integer cargaHoraria, String ementa) {
		this.nomeDisciplina = nomeDisciplina;
		this.codigoDisciplina = codigoDisciplina;
		this.cargaHoraria = cargaHoraria;
		this.ementa = ementa;
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}
	
	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getEmenta() {
		return ementa;
	}
	
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}
	
	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nomeDisciplina=" + nomeDisciplina + 
			   ", codigoDisciplina=" + codigoDisciplina + ", cargaHoraria=" + cargaHoraria + 
			   ", ementa=" + ementa + "]";
	}
} 