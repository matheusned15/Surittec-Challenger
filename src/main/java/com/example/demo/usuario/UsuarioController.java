package com.example.demo.usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "API - Usuarios")
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listaTodosTelefones() {
		return usuarioService.lista();
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<ResponseEntity<Usuario>> buscaUsuario(@PathVariable Long usuarioId) {
		ResponseEntity<Usuario> usuario = usuarioService.busca(usuarioId);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> criaUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.insere(usuario);
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> deletaUsuario(@PathVariable Long usuarioId) {
		return usuarioService.exclui(usuarioId);
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizaUsuario(@PathVariable Long usuarioId, @RequestBody @Valid Usuario usuario) {
		return usuarioService.atualiza(usuarioId, usuario);
	}

}
