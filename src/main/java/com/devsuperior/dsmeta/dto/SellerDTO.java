package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {
	String name;
	
	public SellerDTO() {}
	
	public SellerDTO(String name) {
		this.name = name;
	}
	
	public SellerDTO(Seller seller) {
		this.name = seller.getName();
	}

	public String getName() {
		return name;
	}
}
