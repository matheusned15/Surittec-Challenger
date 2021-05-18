package com.example.demo.email;

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
@Api(tags = "API - Emails")
@RequestMapping("/api/emails")
public class EmailController {

	private final EmailService emailService;

	@GetMapping
	public List<Email> listaTodosEmails() {
		return emailService.lista();
	}

	@GetMapping("/{emailId}")
	public ResponseEntity<ResponseEntity<Email>> buscaEmail(@PathVariable Long emailId) {
		ResponseEntity<Email> email = emailService.busca(emailId);
		return ResponseEntity.ok(email);
	}

	@PostMapping
	public ResponseEntity<Email> criaEmail(@Valid @RequestBody Email email) {
		return emailService.insere(email);
	}

	@DeleteMapping("/{emailId}")
	public ResponseEntity<Void> deletaEmail(@PathVariable Long emailId) {
		return emailService.exclui(emailId);
	}

	@PutMapping("/{emailId}")
	public ResponseEntity<Email> atualizaEmail(@PathVariable Long emailId, @RequestBody @Valid Email email) {
		return emailService.atualiza(emailId, email);
	}

}
