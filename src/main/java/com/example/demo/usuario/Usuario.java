package com.example.demo.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.example.demo.base.BaseEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String login;

	@NotBlank
	private String password;

	@Column(name = "TIPO_USUARIO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
}