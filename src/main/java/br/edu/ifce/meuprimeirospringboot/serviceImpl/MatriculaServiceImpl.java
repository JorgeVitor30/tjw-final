package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import br.edu.ifce.meuprimeirospringboot.dto.MatriculaDTO;
import br.edu.ifce.meuprimeirospringboot.enums.StatusMatricula;
import br.edu.ifce.meuprimeirospringboot.models.Aluno;
import br.edu.ifce.meuprimeirospringboot.models.Matricula;
import br.edu.ifce.meuprimeirospringboot.models.Turma;
import br.edu.ifce.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.ifce.meuprimeirospringboot.repository.MatriculaRepository;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.ifce.meuprimeirospringboot.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    private MatriculaDTO converterParaDTO(Matricula matricula) {
        if (matricula == null) return null;
        MatriculaDTO dto = new MatriculaDTO();
        dto.setId(matricula.getId());
        dto.setAlunoId(matricula.getAluno().getId());
        dto.setTurmaId(matricula.getTurma().getId());
        dto.setDataMatricula(matricula.getDataMatricula());
        dto.setStatusMatricula(matricula.getStatusMatricula());
        dto.setSemestre(matricula.getSemestre());
        dto.setDataCancelamento(matricula.getDataCancelamento());
        return dto;
    }

    private Matricula converterParaEntity(MatriculaDTO dto) {
        if (dto == null) return null;
        Matricula matricula = new Matricula();
        matricula.setId(dto.getId());
        Optional<Aluno> aluno = alunoRepository.findById(dto.getAlunoId());
        Optional<Turma> turma = turmaRepository.findById(dto.getTurmaId());
        if (!aluno.isPresent()) throw new RuntimeException("Aluno não encontrado com ID: " + dto.getAlunoId());
        if (!turma.isPresent()) throw new RuntimeException("Turma não encontrada com ID: " + dto.getTurmaId());
        matricula.setAluno(aluno.get());
        matricula.setTurma(turma.get());
        matricula.setDataMatricula(dto.getDataMatricula());
        matricula.setStatusMatricula(dto.getStatusMatricula());
        matricula.setSemestre(dto.getSemestre());
        matricula.setDataCancelamento(dto.getDataCancelamento());
        return matricula;
    }

    @Override
    public MatriculaDTO salvar(MatriculaDTO matriculaDTO) {
        if (matriculaDTO.getAlunoId() == null) throw new RuntimeException("ID do aluno é obrigatório");
        if (matriculaDTO.getTurmaId() == null) throw new RuntimeException("ID da turma é obrigatório");
        if (matriculaDTO.getDataMatricula() == null) matriculaDTO.setDataMatricula(LocalDate.now());
        if (matriculaDTO.getStatusMatricula() == null) matriculaDTO.setStatusMatricula(StatusMatricula.ATIVA);
        if (matriculaDTO.getSemestre() == null || matriculaDTO.getSemestre().trim().isEmpty()) throw new RuntimeException("Semestre é obrigatório");
        
        if (matriculaRepository.existsByAlunoIdAndTurmaId(matriculaDTO.getAlunoId(), matriculaDTO.getTurmaId())) {
            throw new RuntimeException("Aluno já está matriculado nesta turma");
        }
        
        Matricula matricula = converterParaEntity(matriculaDTO);
        Matricula matriculaSalva = matriculaRepository.save(matricula);
        return converterParaDTO(matriculaSalva);
    }

    @Override
    public MatriculaDTO atualizar(Long id, MatriculaDTO matriculaDTO) {
        if (!matriculaRepository.existsById(id)) throw new RuntimeException("Matrícula não encontrada com ID: " + id);
        matriculaDTO.setId(id);
        Matricula matricula = converterParaEntity(matriculaDTO);
        Matricula matriculaAtualizada = matriculaRepository.save(matricula);
        return converterParaDTO(matriculaAtualizada);
    }

    @Override
    public void deletar(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        if (!matricula.isPresent()) throw new RuntimeException("Matrícula não encontrada com ID: " + id);
        matriculaRepository.delete(matricula.get());
    }

    @Override
    public Optional<MatriculaDTO> buscarPorId(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.map(this::converterParaDTO);
    }

    @Override
    public List<MatriculaDTO> buscarTodos() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorAlunoId(Long alunoId) {
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorTurmaId(Long turmaId) {
        List<Matricula> matriculas = matriculaRepository.findByTurmaId(turmaId);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorStatus(StatusMatricula status) {
        List<Matricula> matriculas = matriculaRepository.findByStatusMatricula(status);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorSemestre(String semestre) {
        List<Matricula> matriculas = matriculaRepository.findBySemestre(semestre);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorDataMatricula(LocalDate dataMatricula) {
        List<Matricula> matriculas = matriculaRepository.findByDataMatricula(dataMatricula);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarPorDataCancelamento(LocalDate dataCancelamento) {
        List<Matricula> matriculas = matriculaRepository.findByDataCancelamento(dataCancelamento);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> buscarMatriculasAtivasPorAlunoId(Long alunoId) {
        List<Matricula> matriculas = matriculaRepository.findByAlunoIdAndStatusMatricula(alunoId, StatusMatricula.ATIVA);
        return matriculas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
} 