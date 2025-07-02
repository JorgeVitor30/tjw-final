package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.models.Aluno;
import br.edu.ifce.meuprimeirospringboot.dto.AlunoDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Genero;
import br.edu.ifce.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.ifce.meuprimeirospringboot.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	private AlunoDTO converterParaDTO(Aluno aluno) {
		if (aluno == null) return null;
		
		AlunoDTO dto = new AlunoDTO();
		dto.setId(aluno.getId());
		dto.setNome(aluno.getNome());
		dto.setDataNascimento(aluno.getDataNascimento());
		dto.setGenero(aluno.getGenero());
		dto.setCpf(aluno.getCpf());
		dto.setEmail(aluno.getEmail());
		dto.setIdade(aluno.getIdade());
		
		return dto;
	}
	
	private Aluno converterParaEntity(AlunoDTO dto) {
		if (dto == null) return null;
		
		Aluno aluno = new Aluno();
		aluno.setId(dto.getId());
		aluno.setNome(dto.getNome());
		aluno.setDataNascimento(dto.getDataNascimento());
		aluno.setGenero(dto.getGenero());
		aluno.setCpf(dto.getCpf());
		aluno.setEmail(dto.getEmail());
		
		return aluno;
	}

	@Override
	public AlunoDTO salvar(AlunoDTO alunoDTO) {
		// Validações antes de salvar
		if (alunoDTO.getCpf() != null && existePorCpf(alunoDTO.getCpf())) {
			throw new RuntimeException("CPF já cadastrado: " + alunoDTO.getCpf());
		}
		
		if (alunoDTO.getEmail() != null && existePorEmail(alunoDTO.getEmail())) {
			throw new RuntimeException("Email já cadastrado: " + alunoDTO.getEmail());
		}
		
		Aluno aluno = converterParaEntity(alunoDTO);
		Aluno alunoSalvo = alunoRepository.save(aluno);
		return converterParaDTO(alunoSalvo);
	}
	
	@Override
	public AlunoDTO atualizar(Long id, AlunoDTO alunoDTO) {
		if (!existePorId(id)) {
			throw new RuntimeException("Aluno não encontrado com ID: " + id);
		}

		if (alunoDTO.getCpf() != null) {
			Optional<AlunoDTO> alunoExistente = buscarPorCpf(alunoDTO.getCpf());
			if (alunoExistente.isPresent() && !alunoExistente.get().getId().equals(id)) {
				throw new RuntimeException("CPF já cadastrado para outro aluno: " + alunoDTO.getCpf());
			}
		}

		if (alunoDTO.getEmail() != null) {
			Optional<AlunoDTO> alunoExistente = buscarPorEmail(alunoDTO.getEmail());
			if (alunoExistente.isPresent() && !alunoExistente.get().getId().equals(id)) {
				throw new RuntimeException("Email já cadastrado para outro aluno: " + alunoDTO.getEmail());
			}
		}
		
		alunoDTO.setId(id);
		Aluno aluno = converterParaEntity(alunoDTO);
		Aluno alunoAtualizado = alunoRepository.save(aluno);
		return converterParaDTO(alunoAtualizado);
	}
	
	@Override
	public void deletar(Long id) {
		alunoRepository.deleteById(id);
	}
	
	@Override
	public Optional<AlunoDTO> buscarPorId(Long id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.map(this::converterParaDTO);
	}
	
	@Override
	public List<AlunoDTO> buscarTodos() {
		List<Aluno> alunos = alunoRepository.findAll();
		return alunos.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<AlunoDTO> buscarPorNome(String nome) {
		List<Aluno> alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
		return alunos.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<AlunoDTO> buscarPorCpf(String cpf) {
		Optional<Aluno> aluno = alunoRepository.findByCpf(cpf);
		return aluno.map(this::converterParaDTO);
	}
	
	@Override
	public Optional<AlunoDTO> buscarPorEmail(String email) {
		Optional<Aluno> aluno = alunoRepository.findByEmail(email);
		return aluno.map(this::converterParaDTO);
	}
	
	@Override
	public List<AlunoDTO> buscarPorGenero(Genero genero) {
		List<Aluno> alunos = alunoRepository.findByGenero(genero);
		return alunos.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<AlunoDTO> buscarTodosOrdenadosPorNome() {
		List<Aluno> alunos = alunoRepository.findAllByOrderByNomeAsc();
		return alunos.stream()
				.map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean existePorCpf(String cpf) {
		return alunoRepository.existsByCpf(cpf);
	}
	
	@Override
	public boolean existePorEmail(String email) {
		return alunoRepository.existsByEmail(email);
	}
	
	@Override
	public boolean existePorId(Long id) {
		return alunoRepository.existsById(id);
	}
} 