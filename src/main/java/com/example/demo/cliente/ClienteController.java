package com.example.demo.cliente;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "API - Clientes")
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	@GetMapping
	public List<Cliente> listaTodosCliente() {
		return clienteService.lista();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ResponseEntity<Cliente>> buscaCliente(@PathVariable Long clienteId) {
		ResponseEntity<Cliente> cliente = clienteService.busca(clienteId);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> criaCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.insere(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletaCliente(@PathVariable Long clienteId) {
		return clienteService.exclui(clienteId);
	}


	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long clienteId, @RequestBody @Valid Cliente cliente) {
		return clienteService.atualiza(clienteId, cliente);
	}

}
