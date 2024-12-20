package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;
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
	
	public Page<ReportMinDTO> report(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate startDate = minDate != null && !"".equals(minDate) ? LocalDate.parse(minDate) : getToday().minusYears(1L);
		LocalDate finalDate = maxDate != null && !"".equals(maxDate) ? LocalDate.parse(maxDate) : getToday();
		
		Page<Sale> vendas = repository.report(startDate, finalDate, name, pageable);
		
		return vendas.map(x -> new ReportMinDTO(x));
	}

	public Page<SummaryDTO> sumary(String minDate, String maxDate, Pageable pageable) {
		if("".equals(minDate) || "".equals(maxDate)) {
			LocalDate startDate = getToday().minusYears(1L);
			LocalDate finalDate = getToday();
			
			minDate = startDate.toString();
			maxDate = finalDate.toString();
		}
		
		Page<SummaryProjection> page = repository.summary(minDate, maxDate, pageable);
		
		return page.map(x -> new SummaryDTO(x));
	}
	
	private LocalDate getToday() {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		return today;
	}
}
