package br.edu.ifce.meuprimeirospringboot.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifce.meuprimeirospringboot.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "data_nascimento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", nullable = false)
	private Genero genero;
	
	@Column(length = 11, unique = true, nullable = false, updatable = true)
	private String cpf;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Transient
	private int idade;
	
	// Construtores
	public Aluno() {}
	
	public Aluno(String nome, LocalDate dataNascimento, Genero genero, String cpf, String email) {
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
		if (dataNascimento != null) {
			return LocalDate.now().getYear() - dataNascimento.getYear();
		}
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + 
			   ", genero=" + genero + ", cpf=" + cpf + ", email=" + email + "]";
	}
} 