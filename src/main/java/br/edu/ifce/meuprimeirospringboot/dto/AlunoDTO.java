package br.edu.ifce.meuprimeirospringboot.dto;

import java.time.LocalDate;

import br.edu.ifce.meuprimeirospringboot.enums.Genero;

public class AlunoDTO {
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Genero genero;
	private String cpf;
	private String email;
	private int idade;
	
	// Construtores
	public AlunoDTO() {}
	
	public AlunoDTO(Long id, String nome, LocalDate dataNascimento, Genero genero, String cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cpf = cpf;
		this.email = email;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "AlunoDTO [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + 
			   ", genero=" + genero + ", cpf=" + cpf + ", email=" + email + ", idade=" + idade + "]";
	}
} 