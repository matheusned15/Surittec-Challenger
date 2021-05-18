package com.example.demo.cliente;

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
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public List<Cliente> lista() {
		return clienteRepository.findAll();
	}

	public ResponseEntity<Cliente> busca(Long clienteId) {
		Optional<Cliente> client = clienteRepository.findById(clienteId);
		return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

	}

	@Transactional
	public ResponseEntity<Cliente> insere(Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	public ResponseEntity<Cliente> atualiza(Long clienteId, Cliente cliente) {
		Optional<Cliente> buscaCliente = clienteRepository.findById(clienteId);
		if (buscaCliente.isPresent()) {
			BeanUtils.copyProperties(cliente, buscaCliente.get(), "codigo");
			Cliente salva = clienteRepository.save(buscaCliente.get());
			return ResponseEntity.ok(salva);

		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<Void> exclui(Long clienteId) {
		Optional<Cliente> buscaCliente = clienteRepository.findById(clienteId);
		if (buscaCliente.isPresent()) {
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}