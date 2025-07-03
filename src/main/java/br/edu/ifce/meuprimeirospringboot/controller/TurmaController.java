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

import br.edu.ifce.meuprimeirospringboot.dto.TurmaDTO;
import br.edu.ifce.meuprimeirospringboot.service.TurmaService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@PostMapping
	public ResponseEntity<?> criarTurma(@RequestBody TurmaDTO turmaDTO) {
		TurmaDTO turmaSalva = turmaService.salvar(turmaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva);
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaDTO>> buscarTodasTurmas() {
		List<TurmaDTO> turmas = turmaService.buscarTodos();
		return ResponseEntity.ok(turmas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarTurmaPorId(@PathVariable Long id) {
		Optional<TurmaDTO> turma = turmaService.buscarPorId(id);
		if (turma.isPresent()) {
			return ResponseEntity.ok(turma.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) {
		TurmaDTO turmaAtualizada = turmaService.atualizar(id, turmaDTO);
		return ResponseEntity.ok(turmaAtualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarTurma(@PathVariable Long id) {
		turmaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
} 