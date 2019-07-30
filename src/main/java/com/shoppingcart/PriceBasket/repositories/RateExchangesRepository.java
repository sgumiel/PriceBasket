package com.shoppingcart.PriceBasket.repositories;

public interface RateExchangesRepository {
	
	public Double getRateExange(final Integer currencyCodeIn, final Integer currencyCodeOut);

}
