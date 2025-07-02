package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.meuprimeirospringboot.dto.ProfessorDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;

public interface ProfessorService {
	
	ProfessorDTO salvar(ProfessorDTO professorDTO);
	ProfessorDTO atualizar(Long id, ProfessorDTO professorDTO);
	void deletar(Long id);
	Optional<ProfessorDTO> buscarPorId(Long id);
	List<ProfessorDTO> buscarTodos();
	
	List<ProfessorDTO> buscarPorNome(String nome);
	Optional<ProfessorDTO> buscarPorCpf(String cpf);
	Optional<ProfessorDTO> buscarPorEmail(String email);
	Optional<ProfessorDTO> buscarPorTelefone(String telefone);
	
	boolean existePorCpf(String cpf);
	boolean existePorEmail(String email);
	boolean existePorTelefone(String telefone);
	boolean existePorId(Long id);
} 