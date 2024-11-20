package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class ReportMinDTO {
	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;
	
	public ReportMinDTO(Sale sale) {
		this.id = sale.getId();
		this.amount = sale.getAmount();
		this.date = sale.getDate();
		this.sellerName = sale.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	
	
	

}
