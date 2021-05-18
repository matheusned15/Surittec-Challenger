package com.example.demo.telefone;

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
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;

    @Transactional(readOnly = true)
    public List<com.example.demo.telefone.Telefone> lista() {
        return telefoneRepository.findAll();
    }

    public ResponseEntity<com.example.demo.telefone.Telefone> busca(Long telefoneId) {
        Optional<com.example.demo.telefone.Telefone> telefone = telefoneRepository.findById(telefoneId);
        return telefone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @Transactional
    public ResponseEntity<com.example.demo.telefone.Telefone> insere(com.example.demo.telefone.Telefone telefone) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneRepository.save(telefone));
    }

    public ResponseEntity<com.example.demo.telefone.Telefone> atualiza(Long telefoneId, com.example.demo.telefone.Telefone telefone) {
        Optional<com.example.demo.telefone.Telefone> buscaTelefone = telefoneRepository.findById(telefoneId);
        if (buscaTelefone.isPresent()) {
            BeanUtils.copyProperties(telefone, buscaTelefone.get(), "telefoneId");
            com.example.demo.telefone.Telefone salva = telefoneRepository.save(buscaTelefone.get());
            return ResponseEntity.ok(salva);

        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> exclui(Long telefoneId) {
        Optional<Telefone> buscaTelefone = telefoneRepository.findById(telefoneId);
        if (buscaTelefone.isPresent()) {
            telefoneRepository.deleteById(telefoneId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}