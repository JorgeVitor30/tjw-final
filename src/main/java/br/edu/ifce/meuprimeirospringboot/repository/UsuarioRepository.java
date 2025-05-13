package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long > {

	@Query("select u from Usuario u")
	List<Usuario> findAllUsuario();
	
	@Query("select u from Usuario u where u.cpf = :cpf ")
	Usuario findUserByCPF(String cpf);
	
	Usuario findByCpf(String cpf);
	@Query("SELECT u from Usuario u where u.raca = br.edu.ifce.enums.Raca.Negro")
	List<Usuario> usuariosNegros();
	
	@Query("select u from Usuario u where u.dtNascimento < :dtNascimento")
	List<Usuario> nascidosAntes2025(Date dtNascimento);
}
