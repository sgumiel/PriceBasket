package com.shoppingcart.PriceBasket.services;

public interface ExchangeService {
	
	public Double exchange(Double amount, Integer currencyCodeIn, Integer currencyCodeOut);

}
