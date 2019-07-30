package com.shoppingcart.PriceBasket.offers;

import java.util.List;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;

@FunctionalInterface
public interface Offer {
	
	public abstract List<Discount> applyOffer(final Cart cart);

}
