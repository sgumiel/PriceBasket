package com.shoppingcart.PriceBasket.services;

import java.util.List;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;

public interface DiscountsCalcultatorService {
	
	public List<Discount> calculateDiscounts(final Cart cart);

}
