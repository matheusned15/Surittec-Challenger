package com.example.demo.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public List<Usuario> lista() {
		return usuarioRepository.findAll();
	}

	public ResponseEntity<Usuario> busca(Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

	}

	@Transactional
	public ResponseEntity<Usuario> insere(Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}

	public ResponseEntity<Usuario> atualiza(Long usuarioId, Usuario usuario) {
		Optional<Usuario> buscaUsuario = usuarioRepository.findById(usuarioId);
		if (buscaUsuario.isPresent()) {
			BeanUtils.copyProperties(usuario, buscaUsuario.get(), "usuarioId");
			Usuario salva = usuarioRepository.save(buscaUsuario.get());
			return ResponseEntity.ok(salva);

		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> exclui(Long usuarioId) {
		Optional<Usuario> buscaUsuario = usuarioRepository.findById(usuarioId);
		if (buscaUsuario.isPresent()) {
			usuarioRepository.deleteById(usuarioId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
