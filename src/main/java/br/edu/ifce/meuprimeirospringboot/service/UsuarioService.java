package br.edu.ifce.meuprimeirospringboot.service;


import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
public interface UsuarioService {
	Usuario buscarPorCPF(String cpf);
}
