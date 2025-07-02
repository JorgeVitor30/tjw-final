package br.edu.ifce.meuprimeirospringboot.dto;

public class DisciplinaDTO {
	private Long id;
	private String nomeDisciplina;
	private String codigoDisciplina;
	private Integer cargaHoraria;
	private String ementa;
	
	// Construtores
	public DisciplinaDTO() {}
	
	public DisciplinaDTO(Long id, String nomeDisciplina, String codigoDisciplina, Integer cargaHoraria, String ementa) {
		this.id = id;
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
		return "DisciplinaDTO [id=" + id + ", nomeDisciplina=" + nomeDisciplina + 
			   ", codigoDisciplina=" + codigoDisciplina + ", cargaHoraria=" + cargaHoraria + 
			   ", ementa=" + ementa + "]";
	}
} 