package com.example.demo.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import com.example.demo.base.BaseEntity;
import com.example.demo.email.Email;
import com.example.telefone.Telefone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends BaseEntity {

	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;

	@CPF
	private String cpf;

	@NotBlank
	private String cep;

	@NotBlank
	private String logradouro;

	@NotBlank
	private String bairro;

	@NotBlank
	private String localidade;

	@NotBlank
	private String uf;

	private String unidade;

	private String ibge;

	private String complemento;

	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones;

	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Email> emails;
}
