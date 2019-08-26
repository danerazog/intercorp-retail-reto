package com.intercorp.retail.reto.clientes.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intercorp.retail.reto.clientes.model.Cliente;
import com.intercorp.retail.reto.clientes.model.EsperanzaVida;
import com.intercorp.retail.reto.clientes.model.KpiCliente;
import com.intercorp.retail.reto.clientes.repository.ClienteRepository;
import com.intercorp.retail.reto.clientes.repository.EsperanzaVidaRepository;
import com.intercorp.retail.reto.clientes.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EsperanzaVidaRepository esperanzaVidaRepository;

	@Override
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		//Se calcula en caso no se haya calculado al insertar
		return clientes.stream().map(c -> {
			if(c.getFechaProbableMuerte() == null)
				c.setFechaProbableMuerte(getFechaProbableMuerte(c.getFechaNacimiento()));
			
			return c;
		}).collect(Collectors.toList());
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		cliente.setFechaProbableMuerte(getFechaProbableMuerte(cliente.getFechaNacimiento()));
		return clienteRepository.insert(cliente);
	}
	
	private String getFechaProbableMuerte(String fechaNacimientoStr) {
		String fechaProbableMuerte = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
		Integer anioNacimiento = fechaNacimiento.getYear();
		
		List<EsperanzaVida> esperanzaVidas = esperanzaVidaRepository.findAll();
		
		//Buscamos si esta registrado la esperanza de vida para el año de nacimiento
		EsperanzaVida esperanzaVida = esperanzaVidas.stream()
				.filter(e -> anioNacimiento.equals(e.getAnioNacimiento()))
				.findAny()
				.orElse(null);

		if (esperanzaVida != null) {
			//Si lo encuentra, convertimos el año a entero para redondear y le sumamos al año de nacimiento
			log.info("Encontro esperanza de vida para año: " + anioNacimiento);
			log.info("Esperanza de vida: " + esperanzaVida.getEsperanzaVida());
			Integer anioMuerte = anioNacimiento + esperanzaVida.getEsperanzaVida().intValue();
			log.info("Año de muerte: " + anioMuerte);
			LocalDate fechaMuerte = LocalDate.of(anioMuerte, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
			fechaProbableMuerte = fechaMuerte.format(formatter);
		}
		
		return fechaProbableMuerte;
	}
	
	@Override
	public KpiCliente getKpiClientes() {
		//Calculamos el promedio de edades
		List<Cliente> clientes = clienteRepository.findAll();
		Integer sum = clientes.stream()
			.mapToInt(x -> x.getEdad())
			.sum();
		Double prom = Double.valueOf(sum) / clientes.size();
		
		log.info("sum: " + sum);
		log.info("prom: " + prom);
		
		//Calculamos la desviacion estandar
		Double desv = clientes.stream()
			.map(x -> Double.valueOf(x.getEdad()))
			.reduce(0.0, (a, b) -> a + Math.pow(b - prom, 2));
		desv = Math.sqrt(desv / clientes.size());
		
		log.info("desv: " + desv);
		
		return new KpiCliente(prom, desv);
	}

	@Override
	public void deleteCliente(String id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}
}
