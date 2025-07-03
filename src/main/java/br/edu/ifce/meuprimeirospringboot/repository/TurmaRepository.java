package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	List<Turma> findBySemestre(String semestre);
	
	Optional<Turma> findByCodigoTurma(String codigoTurma);
	
	List<Turma> findByDisciplinaId(Long disciplinaId);
	
	List<Turma> findByProfessorId(Long professorId);
	
	List<Turma> findBySala(String sala);
	
	List<Turma> findByVagasTotaisGreaterThan(Integer vagasTotais);
	
	List<Turma> findByVagasTotaisLessThan(Integer vagasTotais);
	
	@Query("SELECT t FROM Turma t WHERE t.disciplina.id = :disciplinaId AND t.semestre = :semestre")
	List<Turma> findByDisciplinaIdAndSemestre(@Param("disciplinaId") Long disciplinaId, @Param("semestre") String semestre);
	
	@Query("SELECT t FROM Turma t WHERE t.professor.id = :professorId AND t.semestre = :semestre")
	List<Turma> findByProfessorIdAndSemestre(@Param("professorId") Long professorId, @Param("semestre") String semestre);
	
	@Query("SELECT t FROM Turma t WHERE t.codigoTurma = :codigo OR t.sala = :sala")
	List<Turma> findByCodigoTurmaOrSala(@Param("codigo") String codigo, @Param("sala") String sala);
	
	boolean existsByCodigoTurma(String codigoTurma);
	
	List<Turma> findAllByOrderBySemestreAsc();
	
	List<Turma> findAllByOrderByCodigoTurmaAsc();
	
	List<Turma> findAllByOrderByDisciplinaNomeDisciplinaAsc();
} 