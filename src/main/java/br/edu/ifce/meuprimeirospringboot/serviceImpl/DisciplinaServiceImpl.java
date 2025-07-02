package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.models.Disciplina;
import br.edu.ifce.meuprimeirospringboot.dto.DisciplinaDTO;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	private DisciplinaDTO converterParaDTO(Disciplina disciplina) {
		if (disciplina == null) return null;
		
		DisciplinaDTO dto = new DisciplinaDTO();
		dto.setId(disciplina.getId());
		dto.setNomeDisciplina(disciplina.getNomeDisciplina());
		dto.setCodigoDisciplina(disciplina.getCodigoDisciplina());
		dto.setCargaHoraria(disciplina.getCargaHoraria());
		dto.setEmenta(disciplina.getEmenta());
		
		return dto;
	}
	
	private Disciplina converterParaEntity(DisciplinaDTO dto) {
		if (dto == null) return null;
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(dto.getId());
		disciplina.setNomeDisciplina(dto.getNomeDisciplina());
		disciplina.setCodigoDisciplina(dto.getCodigoDisciplina());
		disciplina.setCargaHoraria(dto.getCargaHoraria());
		disciplina.setEmenta(dto.getEmenta());
		
		return disciplina;
	}

	@Override
	public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) {
		if (disciplinaDTO.getNomeDisciplina() == null || disciplinaDTO.getNomeDisciplina().trim().isEmpty()) {
			throw new RuntimeException("Nome da disciplina é obrigatório");
		}
		
		if (disciplinaDTO.getCodigoDisciplina() == null || disciplinaDTO.getCodigoDisciplina().trim().isEmpty()) {
			throw new RuntimeException("Código da disciplina é obrigatório");
		}
		
		if (disciplinaDTO.getCargaHoraria() == null || disciplinaDTO.getCargaHoraria() <= 0) {
			throw new RuntimeException("Carga horária deve ser maior que zero");
		}
		
		if (disciplinaDTO.getEmenta() == null || disciplinaDTO.getEmenta().trim().isEmpty()) {
			throw new RuntimeException("Ementa é obrigatória");
		}
		
		String codigoLimpo = disciplinaDTO.getCodigoDisciplina().trim().toUpperCase();
		disciplinaDTO.setCodigoDisciplina(codigoLimpo);
		
		if (existePorCodigoDisciplina(codigoLimpo)) {
			throw new RuntimeException("Código da disciplina já cadastrado: " + codigoLimpo);
		}
		
		Disciplina disciplina = converterParaEntity(disciplinaDTO);
		Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
		return converterParaDTO(disciplinaSalva);
	}
	
	@Override
	public DisciplinaDTO atualizar(Long id, DisciplinaDTO disciplinaDTO) {
		if (!existePorId(id)) {
			throw new RuntimeException("Disciplina não encontrada com ID: " + id);
		}

		if (disciplinaDTO.getNomeDisciplina() == null || disciplinaDTO.getNomeDisciplina().trim().isEmpty()) {
			throw new RuntimeException("Nome da disciplina é obrigatório");
		}
		
		if (disciplinaDTO.getCodigoDisciplina() == null || disciplinaDTO.getCodigoDisciplina().trim().isEmpty()) {
			throw new RuntimeException("Código da disciplina é obrigatório");
		}
		
		if (disciplinaDTO.getCargaHoraria() == null || disciplinaDTO.getCargaHoraria() <= 0) {
			throw new RuntimeException("Carga horária deve ser maior que zero");
		}
		
		if (disciplinaDTO.getEmenta() == null || disciplinaDTO.getEmenta().trim().isEmpty()) {
			throw new RuntimeException("Ementa é obrigatória");
		}
		
		String codigoLimpo = disciplinaDTO.getCodigoDisciplina().trim().toUpperCase();
		disciplinaDTO.setCodigoDisciplina(codigoLimpo);

		Optional<DisciplinaDTO> disciplinaExistente = buscarPorCodigoDisciplina(codigoLimpo);
		if (disciplinaExistente.isPresent() && !disciplinaExistente.get().getId().equals(id)) {
			throw new RuntimeException("Código da disciplina já cadastrado para outra disciplina: " + codigoLimpo);
		}
		
		disciplinaDTO.setId(id);
		Disciplina disciplina = converterParaEntity(disciplinaDTO);
		Disciplina disciplinaAtualizada = disciplinaRepository.save(disciplina);
		return converterParaDTO(disciplinaAtualizada);
	}
	
	@Override
	public void deletar(Long id) {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		if (!disciplina.isPresent()) {
			throw new RuntimeException("Disciplina não encontrada com ID: " + id);
		}
		
		try {
			disciplinaRepository.delete(disciplina.get());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar disciplina. Verifique se a disciplina ainda existe: " + id);
		}
	}
	
	@Override
	public Optional<DisciplinaDTO> buscarPorId(Long id) {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return disciplina.map(this::converterParaDTO);
	}
	
	@Override
	public List<DisciplinaDTO> buscarTodos() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return disciplinas.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<DisciplinaDTO> buscarPorNomeDisciplina(String nomeDisciplina) {
		List<Disciplina> disciplinas = disciplinaRepository.findByNomeDisciplinaContainingIgnoreCase(nomeDisciplina);
		return disciplinas.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<DisciplinaDTO> buscarPorCodigoDisciplina(String codigoDisciplina) {
		Optional<Disciplina> disciplina = disciplinaRepository.findByCodigoDisciplina(codigoDisciplina);
		return disciplina.map(this::converterParaDTO);
	}
	
	@Override
	public boolean existePorCodigoDisciplina(String codigoDisciplina) {
		return disciplinaRepository.existsByCodigoDisciplina(codigoDisciplina);
	}
	
	@Override
	public boolean existePorId(Long id) {
		return disciplinaRepository.existsById(id);
	}
} 