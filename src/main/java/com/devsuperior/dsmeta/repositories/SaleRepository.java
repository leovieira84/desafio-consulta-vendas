package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(value = "SELECT sale FROM Sale sale JOIN FETCH sale.seller seller WHERE sale.date BETWEEN :startDate AND :finalDate AND UPPER(seller.name) LIKE UPPER(CONCAT('%' , :name , '%'))",
			countQuery = "SELECT count(obj) FROM Sale obj JOIN obj.seller")
	public Page<Sale> report(LocalDate startDate, LocalDate finalDate, String name, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT seller.name as sellerName, sum(sale.amount) as total FROM TB_SALES sale "
							+ "JOIN TB_SELLER seller ON seller.id = sale.seller_id "
							+ "WHERE sale.date BETWEEN :minDate AND :maxDate "
							+ "GROUP BY seller.name")
	public Page<SummaryProjection> summary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
