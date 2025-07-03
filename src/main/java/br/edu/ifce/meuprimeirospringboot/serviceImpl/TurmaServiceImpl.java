package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import br.edu.ifce.meuprimeirospringboot.dto.TurmaDTO;
import br.edu.ifce.meuprimeirospringboot.models.Disciplina;
import br.edu.ifce.meuprimeirospringboot.models.Professor;
import br.edu.ifce.meuprimeirospringboot.models.Turma;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.ifce.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.ifce.meuprimeirospringboot.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaServiceImpl implements TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private TurmaDTO converterParaDTO(Turma turma) {
		if (turma == null) return null;
		
		TurmaDTO dto = new TurmaDTO();
		dto.setId(turma.getId());
		dto.setDisciplinaId(turma.getDisciplina().getId());
		dto.setProfessorId(turma.getProfessor().getId());
		dto.setSemestre(turma.getSemestre());
		dto.setCodigoTurma(turma.getCodigoTurma());
		dto.setVagasTotais(turma.getVagasTotais());
		dto.setSala(turma.getSala());
		
		// Campos adicionais para exibição
		dto.setNomeDisciplina(turma.getDisciplina().getNomeDisciplina());
		dto.setNomeProfessor(turma.getProfessor().getNome());
		
		return dto;
	}
	
	private Turma converterParaEntity(TurmaDTO dto) {
		if (dto == null) return null;
		
		Turma turma = new Turma();
		turma.setId(dto.getId());
		turma.setSemestre(dto.getSemestre());
		turma.setCodigoTurma(dto.getCodigoTurma());
		turma.setVagasTotais(dto.getVagasTotais());
		turma.setSala(dto.getSala());
		
		// Buscar e definir a disciplina
		if (dto.getDisciplinaId() != null) {
			Optional<Disciplina> disciplina = disciplinaRepository.findById(dto.getDisciplinaId());
			if (disciplina.isPresent()) {
				turma.setDisciplina(disciplina.get());
			} else {
				throw new RuntimeException("Disciplina não encontrada com ID: " + dto.getDisciplinaId());
			}
		}
		
		// Buscar e definir o professor
		if (dto.getProfessorId() != null) {
			Optional<Professor> professor = professorRepository.findById(dto.getProfessorId());
			if (professor.isPresent()) {
				turma.setProfessor(professor.get());
			} else {
				throw new RuntimeException("Professor não encontrado com ID: " + dto.getProfessorId());
			}
		}
		
		return turma;
	}

	@Override
	public TurmaDTO salvar(TurmaDTO turmaDTO) {
		if (turmaDTO.getDisciplinaId() == null) {
			throw new RuntimeException("ID da disciplina é obrigatório");
		}
		
		if (turmaDTO.getProfessorId() == null) {
			throw new RuntimeException("ID do professor é obrigatório");
		}
		
		if (turmaDTO.getSemestre() == null || turmaDTO.getSemestre().trim().isEmpty()) {
			throw new RuntimeException("Semestre é obrigatório");
		}
		
		if (turmaDTO.getCodigoTurma() == null || turmaDTO.getCodigoTurma().trim().isEmpty()) {
			throw new RuntimeException("Código da turma é obrigatório");
		}
		
		if (turmaDTO.getVagasTotais() == null || turmaDTO.getVagasTotais() <= 0) {
			throw new RuntimeException("Vagas totais deve ser maior que zero");
		}
		
		if (turmaDTO.getSala() == null || turmaDTO.getSala().trim().isEmpty()) {
			throw new RuntimeException("Sala é obrigatória");
		}
		
		String codigoLimpo = turmaDTO.getCodigoTurma().trim().toUpperCase();
		turmaDTO.setCodigoTurma(codigoLimpo);
		
		if (existePorCodigoTurma(codigoLimpo)) {
			throw new RuntimeException("Código da turma já cadastrado: " + codigoLimpo);
		}
		
		Turma turma = converterParaEntity(turmaDTO);
		Turma turmaSalva = turmaRepository.save(turma);
		return converterParaDTO(turmaSalva);
	}
	
	@Override
	public TurmaDTO atualizar(Long id, TurmaDTO turmaDTO) {
		if (!existePorId(id)) {
			throw new RuntimeException("Turma não encontrada com ID: " + id);
		}

		if (turmaDTO.getDisciplinaId() == null) {
			throw new RuntimeException("ID da disciplina é obrigatório");
		}
		
		if (turmaDTO.getProfessorId() == null) {
			throw new RuntimeException("ID do professor é obrigatório");
		}
		
		if (turmaDTO.getSemestre() == null || turmaDTO.getSemestre().trim().isEmpty()) {
			throw new RuntimeException("Semestre é obrigatório");
		}
		
		if (turmaDTO.getCodigoTurma() == null || turmaDTO.getCodigoTurma().trim().isEmpty()) {
			throw new RuntimeException("Código da turma é obrigatório");
		}
		
		if (turmaDTO.getVagasTotais() == null || turmaDTO.getVagasTotais() <= 0) {
			throw new RuntimeException("Vagas totais deve ser maior que zero");
		}
		
		if (turmaDTO.getSala() == null || turmaDTO.getSala().trim().isEmpty()) {
			throw new RuntimeException("Sala é obrigatória");
		}
		
		String codigoLimpo = turmaDTO.getCodigoTurma().trim().toUpperCase();
		turmaDTO.setCodigoTurma(codigoLimpo);

		Optional<TurmaDTO> turmaExistente = buscarPorCodigoTurma(codigoLimpo);
		if (turmaExistente.isPresent() && !turmaExistente.get().getId().equals(id)) {
			throw new RuntimeException("Código da turma já cadastrado para outra turma: " + codigoLimpo);
		}
		
		turmaDTO.setId(id);
		Turma turma = converterParaEntity(turmaDTO);
		Turma turmaAtualizada = turmaRepository.save(turma);
		return converterParaDTO(turmaAtualizada);
	}
	
	@Override
	public void deletar(Long id) {
		Optional<Turma> turma = turmaRepository.findById(id);
		if (!turma.isPresent()) {
			throw new RuntimeException("Turma não encontrada com ID: " + id);
		}
		
		try {
			turmaRepository.delete(turma.get());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar turma. Verifique se a turma ainda existe: " + id);
		}
	}
	
	@Override
	public Optional<TurmaDTO> buscarPorId(Long id) {
		Optional<Turma> turma = turmaRepository.findById(id);
		return turma.map(this::converterParaDTO);
	}
	
	@Override
	public List<TurmaDTO> buscarTodos() {
		List<Turma> turmas = turmaRepository.findAll();
		return turmas.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<TurmaDTO> buscarPorCodigoTurma(String codigoTurma) {
		Optional<Turma> turma = turmaRepository.findByCodigoTurma(codigoTurma);
		return turma.map(this::converterParaDTO);
	}
	
	@Override
	public boolean existePorCodigoTurma(String codigoTurma) {
		return turmaRepository.existsByCodigoTurma(codigoTurma);
	}
	
	@Override
	public boolean existePorId(Long id) {
		return turmaRepository.existsById(id);
	}
} 