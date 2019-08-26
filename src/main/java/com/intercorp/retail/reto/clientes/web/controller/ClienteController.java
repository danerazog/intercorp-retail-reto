package com.intercorp.retail.reto.clientes.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intercorp.retail.reto.clientes.model.Cliente;
import com.intercorp.retail.reto.clientes.model.KpiCliente;
import com.intercorp.retail.reto.clientes.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Obtiene el listado de clientes", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		response = Cliente.class, 
		httpMethod = "GET")
	@GetMapping("/listclientes")
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}

	@ApiOperation(value = "Registra un cliente", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		response = Cliente.class, 
		httpMethod = "POST")
	@PostMapping("/creacliente")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@GetMapping("/kpideclientes")
	public KpiCliente getKpiClientes() {
		return clienteService.getKpiClientes();
	}

	@DeleteMapping("/eliminarcliente/{id}")
	public void deleteCliente(@PathVariable String id) {
		clienteService.deleteCliente(id);
	}
}
