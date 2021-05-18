package com.example.telefone;

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
@Api(tags = "API - Telefones")
@RequestMapping("/api/telefones")
public class TelefoneController {

	private final TelefoneService telefoneService;

	@GetMapping
	public List<Telefone> listaTodosTelefones() {
		return telefoneService.lista();
	}

	@GetMapping("/{telefoneId}")
	public ResponseEntity<ResponseEntity<Telefone>> buscaTelefone(@PathVariable Long telefoneId) {
		ResponseEntity<Telefone> usuario = telefoneService.busca(telefoneId);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Telefone> criaTelefone(@Valid @RequestBody Telefone telefone) {
		return telefoneService.insere(telefone);
	}

	@DeleteMapping("/{telefoneId}")
	public ResponseEntity<Void> deletaTelefone(@PathVariable Long telefoneId) {
		return telefoneService.exclui(telefoneId);
	}

	@PutMapping("/{telefoneId}")
	public ResponseEntity<Telefone> atualizaTelefone(@PathVariable Long telefoneId,
			@RequestBody @Valid Telefone telefone) {
		return telefoneService.atualiza(telefoneId, telefone);
	}
}
