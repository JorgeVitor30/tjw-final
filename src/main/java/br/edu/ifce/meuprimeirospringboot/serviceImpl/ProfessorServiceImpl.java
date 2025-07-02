package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.models.Professor;
import br.edu.ifce.meuprimeirospringboot.dto.ProfessorDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;
import br.edu.ifce.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.ifce.meuprimeirospringboot.service.ProfessorService;
import br.edu.ifce.meuprimeirospringboot.utils.CpfValidator;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private ProfessorDTO converterParaDTO(Professor professor) {
		if (professor == null) return null;
		
		ProfessorDTO dto = new ProfessorDTO();
		dto.setId(professor.getId());
		dto.setNome(professor.getNome());
		dto.setDataNascimento(professor.getDataNascimento());
		dto.setGenero(professor.getGenero());
		dto.setCpf(professor.getCpf());
		dto.setAreaAtuacao(professor.getAreaAtuacao());
		dto.setEmail(professor.getEmail());
		dto.setTelefone(professor.getTelefone());
		dto.setIdade(professor.getIdade());
		
		return dto;
	}
	
	private Professor converterParaEntity(ProfessorDTO dto) {
		if (dto == null) return null;
		
		Professor professor = new Professor();
		professor.setId(dto.getId());
		professor.setNome(dto.getNome());
		professor.setDataNascimento(dto.getDataNascimento());
		professor.setGenero(dto.getGenero());
		professor.setCpf(dto.getCpf());
		professor.setAreaAtuacao(dto.getAreaAtuacao());
		professor.setEmail(dto.getEmail());
		professor.setTelefone(dto.getTelefone());
		
		return professor;
	}

	@Override
	public ProfessorDTO salvar(ProfessorDTO professorDTO) {
		if (professorDTO.getCpf() == null || professorDTO.getCpf().trim().isEmpty()) {
			throw new RuntimeException("CPF é obrigatório");
		}
		
		if (!CpfValidator.isValid(professorDTO.getCpf())) {
			throw new RuntimeException("CPF inválido: " + professorDTO.getCpf());
		}
		
		String cpfLimpo = CpfValidator.limpar(professorDTO.getCpf());
		professorDTO.setCpf(cpfLimpo);
		
		if (existePorCpf(cpfLimpo)) {
			throw new RuntimeException("CPF já cadastrado: " + cpfLimpo);
		}
		
		if (professorDTO.getEmail() != null && existePorEmail(professorDTO.getEmail())) {
			throw new RuntimeException("Email já cadastrado: " + professorDTO.getEmail());
		}
		
		if (professorDTO.getTelefone() != null && existePorTelefone(professorDTO.getTelefone())) {
			throw new RuntimeException("Telefone já cadastrado: " + professorDTO.getTelefone());
		}
		
		Professor professor = converterParaEntity(professorDTO);
		Professor professorSalvo = professorRepository.save(professor);
		return converterParaDTO(professorSalvo);
	}
	
	@Override
	public ProfessorDTO atualizar(Long id, ProfessorDTO professorDTO) {
		if (!existePorId(id)) {
			throw new RuntimeException("Professor não encontrado com ID: " + id);
		}

		if (professorDTO.getCpf() == null || professorDTO.getCpf().trim().isEmpty()) {
			throw new RuntimeException("CPF é obrigatório");
		}
		
		if (!CpfValidator.isValid(professorDTO.getCpf())) {
			throw new RuntimeException("CPF inválido: " + professorDTO.getCpf());
		}
		
		String cpfLimpo = CpfValidator.limpar(professorDTO.getCpf());
		professorDTO.setCpf(cpfLimpo);

		Optional<ProfessorDTO> professorExistente = buscarPorCpf(cpfLimpo);
		if (professorExistente.isPresent() && !professorExistente.get().getId().equals(id)) {
			throw new RuntimeException("CPF já cadastrado para outro professor: " + cpfLimpo);
		}

		if (professorDTO.getEmail() != null) {
			Optional<ProfessorDTO> professorExistenteEmail = buscarPorEmail(professorDTO.getEmail());
			if (professorExistenteEmail.isPresent() && !professorExistenteEmail.get().getId().equals(id)) {
				throw new RuntimeException("Email já cadastrado para outro professor: " + professorDTO.getEmail());
			}
		}
		
		if (professorDTO.getTelefone() != null) {
			Optional<ProfessorDTO> professorExistenteTelefone = buscarPorTelefone(professorDTO.getTelefone());
			if (professorExistenteTelefone.isPresent() && !professorExistenteTelefone.get().getId().equals(id)) {
				throw new RuntimeException("Telefone já cadastrado para outro professor: " + professorDTO.getTelefone());
			}
		}
		
		professorDTO.setId(id);
		Professor professor = converterParaEntity(professorDTO);
		Professor professorAtualizado = professorRepository.save(professor);
		return converterParaDTO(professorAtualizado);
	}
	
	@Override
	public void deletar(Long id) {
		Optional<Professor> professor = professorRepository.findById(id);
		if (!professor.isPresent()) {
			throw new RuntimeException("Professor não encontrado com ID: " + id);
		}
		
		try {
			professorRepository.delete(professor.get());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar professor. Verifique se o professor ainda existe: " + id);
		}
	}
	
	@Override
	public Optional<ProfessorDTO> buscarPorId(Long id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.map(this::converterParaDTO);
	}
	
	@Override
	public List<ProfessorDTO> buscarTodos() {
		List<Professor> professores = professorRepository.findAll();
		return professores.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDTO> buscarPorNome(String nome) {
		List<Professor> professores = professorRepository.findByNomeContainingIgnoreCase(nome);
		return professores.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<ProfessorDTO> buscarPorCpf(String cpf) {
		Optional<Professor> professor = professorRepository.findByCpf(cpf);
		return professor.map(this::converterParaDTO);
	}
	
	@Override
	public Optional<ProfessorDTO> buscarPorEmail(String email) {
		Optional<Professor> professor = professorRepository.findByEmail(email);
		return professor.map(this::converterParaDTO);
	}
	
	@Override
	public Optional<ProfessorDTO> buscarPorTelefone(String telefone) {
		Optional<Professor> professor = professorRepository.findByTelefone(telefone);
		return professor.map(this::converterParaDTO);
	}
	
	@Override
	public List<ProfessorDTO> buscarPorGenero(Genero genero) {
		List<Professor> professores = professorRepository.findByGenero(genero);
		return professores.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ProfessorDTO> buscarPorAreaAtuacao(String areaAtuacao) {
		List<Professor> professores = professorRepository.findByAreaAtuacaoContainingIgnoreCase(areaAtuacao);
		return professores.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ProfessorDTO> buscarTodosOrdenadosPorNome() {
		List<Professor> professores = professorRepository.findAllByOrderByNomeAsc();
		return professores.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean existePorCpf(String cpf) {
		return professorRepository.existsByCpf(cpf);
	}
	
	@Override
	public boolean existePorEmail(String email) {
		return professorRepository.existsByEmail(email);
	}
	
	@Override
	public boolean existePorTelefone(String telefone) {
		return professorRepository.existsByTelefone(telefone);
	}
	
	@Override
	public boolean existePorId(Long id) {
		return professorRepository.existsById(id);
	}
} 