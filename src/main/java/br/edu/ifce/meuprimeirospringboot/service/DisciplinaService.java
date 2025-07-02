package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.meuprimeirospringboot.dto.DisciplinaDTO;

public interface DisciplinaService {
	
	DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO);
	DisciplinaDTO atualizar(Long id, DisciplinaDTO disciplinaDTO);
	void deletar(Long id);
	Optional<DisciplinaDTO> buscarPorId(Long id);
	List<DisciplinaDTO> buscarTodos();
	
	List<DisciplinaDTO> buscarPorNomeDisciplina(String nomeDisciplina);
	Optional<DisciplinaDTO> buscarPorCodigoDisciplina(String codigoDisciplina);
	
	boolean existePorCodigoDisciplina(String codigoDisciplina);
	boolean existePorId(Long id);
} 