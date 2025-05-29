package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.exceptions.UsuarioNaoEncontradoException;
import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;
import br.edu.ifce.meuprimeirospringboot.service.UsuarioService;
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario buscarPorCPF(String cpf) {
		return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(cpf));
    }

	
	
	
}
