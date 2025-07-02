package br.edu.ifce.meuprimeirospringboot.dto;

import java.time.LocalDate;

import br.edu.ifce.meuprimeirospringboot.enums.Genero;

public class ProfessorDTO {
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Genero genero;
	private String cpf;
	private String areaAtuacao;
	private String email;
	private String telefone;
	private int idade;
	
	// Construtores
	public ProfessorDTO() {}
	
	public ProfessorDTO(Long id, String nome, LocalDate dataNascimento, Genero genero, 
	                   String cpf, String areaAtuacao, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cpf = cpf;
		this.areaAtuacao = areaAtuacao;
		this.email = email;
		this.telefone = telefone;
	}
	
	// Getters e Setters
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
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "ProfessorDTO [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + 
			   ", genero=" + genero + ", cpf=" + cpf + ", areaAtuacao=" + areaAtuacao + 
			   ", email=" + email + ", telefone=" + telefone + ", idade=" + idade + "]";
	}
} 