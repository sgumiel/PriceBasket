package com.shoppingcart.PriceBasket.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount implements Serializable {

	private static final long serialVersionUID = 4887255000885126274L;
	
	private String description;
	private Double amount;
	

}
