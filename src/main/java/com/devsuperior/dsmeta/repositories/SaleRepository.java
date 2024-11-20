package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT sale FROM Sale sale JOIN FETCH sale.seller seller WHERE sale.date BETWEEN :startDate AND :finalDate AND UPPER(seller.name) LIKE UPPER(CONCAT('%' , :name , '%'))")
	public List<Sale> report(LocalDate startDate, LocalDate finalDate, String name);
}
