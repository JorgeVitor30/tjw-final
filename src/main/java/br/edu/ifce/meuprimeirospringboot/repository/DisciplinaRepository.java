package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

} 