package com.example.demo.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.demo.base.BaseEntity;
import com.example.demo.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMAIL")
@EqualsAndHashCode(callSuper = true)
public class Email extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@javax.validation.constraints.Email
	private String email;

	@ManyToOne
	@JsonIgnore
	private Cliente cliente;
}