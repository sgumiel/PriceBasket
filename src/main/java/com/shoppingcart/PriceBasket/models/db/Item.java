package com.shoppingcart.PriceBasket.models.db;

import java.io.Serializable;

import com.shoppingcart.PriceBasket.utils.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = 3621074604352110970L;
	
	private String description;
	private Double price;
	private CurrencyCode currency;
	
}
