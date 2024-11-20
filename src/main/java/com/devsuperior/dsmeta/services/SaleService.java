package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<ReportMinDTO> report(String minDate, String maxDate, String name){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate startDate = minDate != null && !"".equals(minDate) ? LocalDate.parse(minDate) : today.minusYears(1L);
		LocalDate finalDate = maxDate != null && !"".equals(maxDate) ? LocalDate.parse(maxDate) : today;
		
		List<Sale> vendas = repository.report(startDate, finalDate, name);
		
		return vendas.stream().map(x -> new ReportMinDTO(x)).collect(Collectors.toList());
	}
}
