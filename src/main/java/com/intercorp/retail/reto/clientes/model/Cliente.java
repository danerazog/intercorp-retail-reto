package com.intercorp.retail.reto.clientes.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cliente {

	@Id
	private String id;
	
	@ApiModelProperty(
	  name = "nombre",
      value = "Nombre del cliente",
      example = "Juan",
      dataType = "string")
	@Valid
	@NotNull(message = "El campo nombre es requerido")
	@NotEmpty(message = "El campo nombre no debe ser vacío")
	private String nombre;
	
	@ApiModelProperty(
	  name = "apellido",
      value = "Apellido del cliente",
	  example = "Perez",
	  dataType = "string")
	@NotNull(message = "El campo apellido es requerido")
	@NotEmpty(message = "El campo apellido no debe ser vacio")
	private String apellido;
	
	@ApiModelProperty(
	  name = "edad",
	  value = "Edad del cliente",
	  example = "18",
	  dataType = "integer")
	@NotNull(message = "El campo edad es requerido")
	private Integer edad;
	
	@ApiModelProperty(
	  name = "fechaNacimiento",
	  value = "Fecha de nacimiento del cliente en formato dd/MM/yyyy",
	  example = "29/02/1990",
	  dataType = "string")
	@Valid
	@NotNull(message = "El campo fechaNacimiento es requerido")
	@Pattern(regexp = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$", 
		     message= "Fecha debe corresponder al formato: dd/MM/yyyy")
	private String fechaNacimiento;
	
	@ApiModelProperty(
	  name = "fechaProbableMuerte",
	  value = "Fecha probable de muerte en base a la esperanza de vida",
	  example = "29/02/2060",
	  dataType = "string")
	private String fechaProbableMuerte;
}
