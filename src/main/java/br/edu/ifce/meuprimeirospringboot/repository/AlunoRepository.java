package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.models.Aluno;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	

	List<Aluno> findByNomeContainingIgnoreCase(String nome);
	

	Optional<Aluno> findByCpf(String cpf);
	
	Optional<Aluno> findByEmail(String email);
	
	List<Aluno> findByGenero(Genero genero);
	
	@Query("SELECT a FROM Aluno a WHERE a.cpf = :cpf OR a.email = :email")
	List<Aluno> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
	
	boolean existsByCpf(String cpf);
	
	boolean existsByEmail(String email);
	
	List<Aluno> findAllByOrderByNomeAsc();
} 