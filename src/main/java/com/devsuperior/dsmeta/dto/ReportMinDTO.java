package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class ReportMinDTO {
	private Long id;
	private Double amount;
	private LocalDate date;
	private SellerDTO seller;
	
	
	public ReportMinDTO() {}


	public ReportMinDTO(Long id, Double amount, LocalDate date, SellerDTO seller) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.seller = seller;
	}
	
	public ReportMinDTO(Sale sale) {
		this.id = sale.getId();
		this.amount = sale.getAmount();
		this.date = sale.getDate();
		this.seller = new SellerDTO(sale.getSeller());
	}
	

}
