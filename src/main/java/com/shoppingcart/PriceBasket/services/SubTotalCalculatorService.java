package com.shoppingcart.PriceBasket.services;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

public interface SubTotalCalculatorService {
	
	public Double calculateSubtotal(final Cart cart, CurrencyCode currencyCode);

}
