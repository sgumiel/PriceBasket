package com.shoppingcart.PriceBasket.models;

import java.io.Serializable;
import java.util.List;

import com.shoppingcart.PriceBasket.utils.CurrencyCode;

import lombok.Data;

@Data
public class SummarizePay implements Serializable {
	
	private static final long serialVersionUID = -6163172787684834878L;
	
	private Double subtotal;
	private List<Discount> discountsList;
	private Double total;
	private CurrencyCode currencyCode;
	
	public SummarizePay(final Double subtotal, final List<Discount> discountsList, final CurrencyCode currencyCode) {
		this.subtotal = subtotal;
		this.discountsList=discountsList;
		this.currencyCode = currencyCode;
		
		this.total = this.subtotal - this.discountsList.stream().map( discount -> discount.getAmount()).reduce(0.0, Double::sum);
		
	}
	
}
