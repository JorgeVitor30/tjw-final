package br.edu.ifce.meuprimeirospringboot.service;

import br.edu.ifce.meuprimeirospringboot.dto.MatriculaDTO;
import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatriculaService {
    MatriculaDTO salvar(MatriculaDTO matriculaDTO);
    MatriculaDTO atualizar(Long id, MatriculaDTO matriculaDTO);
    void deletar(Long id);
    Optional<MatriculaDTO> buscarPorId(Long id);
    List<MatriculaDTO> buscarTodos();
    List<MatriculaDTO> buscarPorAlunoId(Long alunoId);
    List<MatriculaDTO> buscarPorTurmaId(Long turmaId);
    List<MatriculaDTO> buscarPorStatus(StatusMatricula status);
    List<MatriculaDTO> buscarPorSemestre(String semestre);
    List<MatriculaDTO> buscarPorDataMatricula(LocalDate dataMatricula);
    List<MatriculaDTO> buscarPorDataCancelamento(LocalDate dataCancelamento);
    List<MatriculaDTO> buscarMatriculasAtivasPorAlunoId(Long alunoId);
} 