package com.shoppingcart.PriceBasket.services;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.SummarizePay;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

public interface SummarizePayService {
	
	public SummarizePay createSummarizePay(final Cart cart, final CurrencyCode currency);

}
