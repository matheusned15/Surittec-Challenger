package com.example.telefone;

import com.example.demo.base.BaseEntity;
import com.example.demo.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TELEFONE")
@EqualsAndHashCode(callSuper = true)
public class Telefone extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name = "NUMERO")
	private String numero;

	@NotNull
	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipoTelefone;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;
}
