package br.edu.ifce.meuprimeirospringboot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.dto.CpfDTO;
import br.edu.ifce.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;
@RestController
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	
	// @RequestParam --> GET /usuario?cpf=12345678900
	// @RequestBody --> GET /usuario/12345678900 @GetMapping("/usuario/{cpf}")
	// @PathVariable --> Lê o corpo da requisição (normalmente JSON) e mapeia para um objeto Java.
	
	@PostMapping("/usuario")
    public ResponseEntity<Usuario> getUsuarioPorCPF(@RequestBody CpfDTO dto) {
		

		 Usuario usuario = usuarioService.buscarPorCPF(dto.getCpf());
	        return ResponseEntity.ok(usuario); 
	    }

		
}
