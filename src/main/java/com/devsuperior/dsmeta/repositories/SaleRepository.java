package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(value = "SELECT sale FROM Sale sale JOIN FETCH sale.seller seller WHERE sale.date BETWEEN :startDate AND :finalDate AND UPPER(seller.name) LIKE UPPER(CONCAT('%' , :name , '%'))",
			countQuery = "SELECT count(obj) FROM Sale obj JOIN obj.seller")
	public Page<Sale> report(LocalDate startDate, LocalDate finalDate, String name, Pageable pageable);
}
