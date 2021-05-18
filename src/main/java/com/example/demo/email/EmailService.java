package com.example.demo.email;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final EmailRepository emailRepository;

	@Transactional(readOnly = true)
	public List<Email> lista() {
		return emailRepository.findAll();
	}

	public ResponseEntity<Email> busca(Long emailId) {
		Optional<Email> email = emailRepository.findById(emailId);
		return email.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

	}

	@Transactional
	public ResponseEntity<Email> insere(Email email) {
		return ResponseEntity.status(HttpStatus.CREATED).body(emailRepository.save(email));
	}

	public ResponseEntity<Email> atualiza(Long emailId, @Valid Email email) {
		Optional<Email> buscaEmail = emailRepository.findById(emailId);
		if (buscaEmail.isPresent()) {
			BeanUtils.copyProperties(email, buscaEmail.get(), "emailId");
			Email salva = emailRepository.save(buscaEmail.get());
			return ResponseEntity.ok(salva);

		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> exclui(Long emailId) {
		Optional<Email> buscaTelefone = emailRepository.findById(emailId);
		if (buscaTelefone.isPresent()) {
			emailRepository.deleteById(emailId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
