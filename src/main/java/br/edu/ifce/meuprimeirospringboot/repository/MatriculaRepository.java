package br.edu.ifce.meuprimeirospringboot.repository;

import br.edu.ifce.meuprimeirospringboot.models.Matricula;
import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByAlunoId(Long alunoId);
    List<Matricula> findByTurmaId(Long turmaId);
    List<Matricula> findByStatusMatricula(StatusMatricula statusMatricula);
    List<Matricula> findBySemestre(String semestre);
    List<Matricula> findByDataMatricula(LocalDate dataMatricula);
    List<Matricula> findByDataCancelamento(LocalDate dataCancelamento);
    List<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
    List<Matricula> findByAlunoIdAndStatusMatricula(Long alunoId, StatusMatricula statusMatricula);
} 