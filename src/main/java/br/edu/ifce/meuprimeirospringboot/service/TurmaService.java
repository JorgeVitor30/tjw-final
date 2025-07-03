package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.meuprimeirospringboot.dto.TurmaDTO;

public interface TurmaService {
	
	TurmaDTO salvar(TurmaDTO turmaDTO);
	TurmaDTO atualizar(Long id, TurmaDTO turmaDTO);
	void deletar(Long id);
	Optional<TurmaDTO> buscarPorId(Long id);
	List<TurmaDTO> buscarTodos();
	Optional<TurmaDTO> buscarPorCodigoTurma(String codigoTurma);
	
	boolean existePorCodigoTurma(String codigoTurma);
	boolean existePorId(Long id);
} 