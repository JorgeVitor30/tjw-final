package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.models.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	List<Disciplina> findByNomeDisciplinaContainingIgnoreCase(String nomeDisciplina);
	
	Optional<Disciplina> findByCodigoDisciplina(String codigoDisciplina);
	
	List<Disciplina> findByCargaHoraria(Integer cargaHoraria);
	
	List<Disciplina> findByCargaHorariaGreaterThan(Integer cargaHoraria);
	
	List<Disciplina> findByCargaHorariaLessThan(Integer cargaHoraria);
	
	@Query("SELECT d FROM Disciplina d WHERE d.ementa LIKE %:termo%")
	List<Disciplina> findByEmentaContaining(@Param("termo") String termo);
	
	@Query("SELECT d FROM Disciplina d WHERE d.codigoDisciplina = :codigo OR d.nomeDisciplina LIKE %:nome%")
	List<Disciplina> findByCodigoDisciplinaOrNomeDisciplinaContaining(
		@Param("codigo") String codigo, @Param("nome") String nome);
	
	boolean existsByCodigoDisciplina(String codigoDisciplina);
	
	List<Disciplina> findAllByOrderByNomeDisciplinaAsc();
	
	List<Disciplina> findAllByOrderByCodigoDisciplinaAsc();
} 