package br.edu.ifce.meuprimeirospringboot.service;


import java.util.List;
import br.edu.ifce.meuprimeirospringboot.beans.Usuario;

public interface UsuarioService {
	Usuario buscarPorCPF(String cpf);
	Usuario salvar(Usuario usuario);
	Usuario editar(Long id, Usuario usuarioAtualizado);
	void deletar(Long id);
	List<Usuario> listarTodos();
	Usuario buscarPorId(Long id);
}
