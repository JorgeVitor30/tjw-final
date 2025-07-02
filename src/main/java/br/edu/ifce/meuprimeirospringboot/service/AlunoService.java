package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.meuprimeirospringboot.dto.AlunoDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;

public interface AlunoService {

	AlunoDTO salvar(AlunoDTO alunoDTO);
	AlunoDTO atualizar(Long id, AlunoDTO alunoDTO);
	void deletar(Long id);
	Optional<AlunoDTO> buscarPorId(Long id);
	List<AlunoDTO> buscarTodos();

	List<AlunoDTO> buscarPorNome(String nome);
	Optional<AlunoDTO> buscarPorCpf(String cpf);
	Optional<AlunoDTO> buscarPorEmail(String email);
	List<AlunoDTO> buscarPorGenero(Genero genero);
	List<AlunoDTO> buscarTodosOrdenadosPorNome();

	boolean existePorCpf(String cpf);
	boolean existePorEmail(String email);
	boolean existePorId(Long id);
} 