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

import br.edu.ifce.meuprimeirospringboot.dto.AlunoDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;
import br.edu.ifce.meuprimeirospringboot.service.AlunoService;
import br.edu.ifce.meuprimeirospringboot.utils.CpfValidator;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@PostMapping
	public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO alunoDTO) {
		AlunoDTO alunoSalvo = alunoService.salvar(alunoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> buscarTodosAlunos() {
		List<AlunoDTO> alunos = alunoService.buscarTodos();
		return ResponseEntity.ok(alunos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarAlunoPorId(@PathVariable Long id) {
		Optional<AlunoDTO> aluno = alunoService.buscarPorId(id);
		if (aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<AlunoDTO>> buscarAlunosPorNome(@RequestParam String nome) {
		List<AlunoDTO> alunos = alunoService.buscarPorNome(nome);
		return ResponseEntity.ok(alunos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
		AlunoDTO alunoAtualizado = alunoService.atualizar(id, alunoDTO);
		return ResponseEntity.ok(alunoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarAluno(@PathVariable Long id) {
		alunoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

} 