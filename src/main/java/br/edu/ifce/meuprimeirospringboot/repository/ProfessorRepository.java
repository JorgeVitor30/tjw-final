package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.models.Professor;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	List<Professor> findByNomeContainingIgnoreCase(String nome);
	
	Optional<Professor> findByCpf(String cpf);
	
	Optional<Professor> findByEmail(String email);
	
	Optional<Professor> findByTelefone(String telefone);
	
	List<Professor> findByGenero(Genero genero);
	
	List<Professor> findByAreaAtuacaoContainingIgnoreCase(String areaAtuacao);
	
	@Query("SELECT p FROM Professor p WHERE p.cpf = :cpf OR p.email = :email")
	List<Professor> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
	
	boolean existsByCpf(String cpf);
	
	boolean existsByEmail(String email);
	
	boolean existsByTelefone(String telefone);
	
	List<Professor> findAllByOrderByNomeAsc();
} 