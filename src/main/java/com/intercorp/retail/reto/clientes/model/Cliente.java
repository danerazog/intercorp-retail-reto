package com.intercorp.retail.reto.clientes.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cliente {

	@Id
	private String id;
	
	private String nombre;
	private String apellido;
	private Integer edad;
	private String fechaNacimiento;
}
