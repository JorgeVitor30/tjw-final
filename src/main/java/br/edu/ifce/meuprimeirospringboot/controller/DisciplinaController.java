package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.List;
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

import br.edu.ifce.meuprimeirospringboot.dto.DisciplinaDTO;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@PostMapping
	public ResponseEntity<?> criarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
		DisciplinaDTO disciplinaSalva = disciplinaService.salvar(disciplinaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaSalva);
	}
	
	@GetMapping
	public ResponseEntity<List<DisciplinaDTO>> buscarTodasDisciplinas() {
		List<DisciplinaDTO> disciplinas = disciplinaService.buscarTodos();
		return ResponseEntity.ok(disciplinas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarDisciplinaPorId(@PathVariable Long id) {
		Optional<DisciplinaDTO> disciplina = disciplinaService.buscarPorId(id);
		if (disciplina.isPresent()) {
			return ResponseEntity.ok(disciplina.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/buscar/nome")
	public ResponseEntity<List<DisciplinaDTO>> buscarDisciplinasPorNome(@RequestParam String nome) {
		List<DisciplinaDTO> disciplinas = disciplinaService.buscarPorNomeDisciplina(nome);
		return ResponseEntity.ok(disciplinas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
		DisciplinaDTO disciplinaAtualizada = disciplinaService.atualizar(id, disciplinaDTO);
		return ResponseEntity.ok(disciplinaAtualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarDisciplina(@PathVariable Long id) {
		disciplinaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
} 