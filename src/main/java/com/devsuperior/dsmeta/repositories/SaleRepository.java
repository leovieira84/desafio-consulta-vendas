package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :startDate AND :finalDate AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%' , :name , '%'))")
	public List<Sale> report(LocalDate startDate, LocalDate finalDate, String name);
}
