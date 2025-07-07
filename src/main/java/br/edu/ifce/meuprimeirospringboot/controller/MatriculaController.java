 package br.edu.ifce.meuprimeirospringboot.controller;

import br.edu.ifce.meuprimeirospringboot.dto.MatriculaDTO;
import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import br.edu.ifce.meuprimeirospringboot.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaDTO> criarMatricula(@RequestBody MatriculaDTO matriculaDTO) {
        MatriculaDTO matriculaSalva = matriculaService.salvar(matriculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaSalva);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> buscarTodasMatriculas() {
        List<MatriculaDTO> matriculas = matriculaService.buscarTodos();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarMatriculaPorId(@PathVariable Long id) {
        Optional<MatriculaDTO> matricula = matriculaService.buscarPorId(id);
        return matricula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MatriculaDTO>> buscarMatriculas(
            @RequestParam(required = false) Long alunoId,
            @RequestParam(required = false) Long turmaId,
            @RequestParam(required = false) StatusMatricula status,
            @RequestParam(required = false) String semestre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataMatricula,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCancelamento
    ) {
        List<MatriculaDTO> matriculas;
        if (alunoId != null) {
            matriculas = matriculaService.buscarPorAlunoId(alunoId);
        } else if (turmaId != null) {
            matriculas = matriculaService.buscarPorTurmaId(turmaId);
        } else if (status != null) {
            matriculas = matriculaService.buscarPorStatus(status);
        } else if (semestre != null && !semestre.trim().isEmpty()) {
            matriculas = matriculaService.buscarPorSemestre(semestre);
        } else if (dataMatricula != null) {
            matriculas = matriculaService.buscarPorDataMatricula(dataMatricula);
        } else if (dataCancelamento != null) {
            matriculas = matriculaService.buscarPorDataCancelamento(dataCancelamento);
        } else {
            matriculas = matriculaService.buscarTodos();
        }
        return ResponseEntity.ok(matriculas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> atualizarMatricula(@PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO) {
        MatriculaDTO matriculaAtualizada = matriculaService.atualizar(id, matriculaDTO);
        return ResponseEntity.ok(matriculaAtualizada);
    }

    @GetMapping("/aluno/{alunoId}/ativas")
    public ResponseEntity<List<MatriculaDTO>> buscarMatriculasAtivasPorAluno(@PathVariable Long alunoId) {
        List<MatriculaDTO> matriculas = matriculaService.buscarMatriculasAtivasPorAlunoId(alunoId);
        return ResponseEntity.ok(matriculas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
