package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.meuprimeirospringboot.dto.ProfessorDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;
import br.edu.ifce.meuprimeirospringboot.service.ProfessorService;
import br.edu.ifce.meuprimeirospringboot.utils.CpfValidator;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<?> criarProfessor(@RequestBody ProfessorDTO professorDTO) {
		ProfessorDTO professorSalvo = professorService.salvar(professorDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> buscarTodosProfessores() {
		List<ProfessorDTO> professores = professorService.buscarTodos();
		return ResponseEntity.ok(professores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProfessorPorId(@PathVariable Long id) {
		Optional<ProfessorDTO> professor = professorService.buscarPorId(id);
		if (professor.isPresent()) {
			return ResponseEntity.ok(professor.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<ProfessorDTO>> buscarProfessoresPorNome(@RequestParam String nome) {
		List<ProfessorDTO> professores = professorService.buscarPorNome(nome);
		return ResponseEntity.ok(professores);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
		ProfessorDTO professorAtualizado = professorService.atualizar(id, professorDTO);
		return ResponseEntity.ok(professorAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProfessor(@PathVariable Long id) {
		professorService.deletar(id);
		return ResponseEntity.noContent().build();
	}
} 