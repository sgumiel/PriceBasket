package com.shoppingcart.PriceBasket.offers.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.config.Config;
import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.offers.Offer;
import com.shoppingcart.PriceBasket.services.ExchangeService;

@Service
public class ApplesOffer implements Offer {

	private Double rateDiscount = 0.1;
	
	@Autowired
	private Config config;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Override
	public List<Discount> applyOffer(Cart cart) {
		
		final boolean areTheApples = cart.getItemList().stream().anyMatch( item -> item.getDescription().equals("Apples"));
		
		if(areTheApples) {
			return createDiscount(cart);
		}
		
		return new ArrayList<>();
	}
	
	private List<Discount> createDiscount(Cart cart){
		
		final List<Item>  applesList= cart.getItemList().stream().filter( item -> item.getDescription().equals("Apples")).collect(Collectors.toList());
		final Double priceOfApples = applesList.get(0).getPrice();
		final Integer currencyCode = applesList.get(0).getCurrency().getCode();
		final Double priceInEuros = exchangeService.exchange(priceOfApples, currencyCode, config.getDefaultCurrencyCode());
		final Integer numOfItemApples = applesList.size();
		final Double discountAmount = numOfItemApples * priceInEuros * rateDiscount;
		
		final Discount discount = new Discount("Apples 10% off",discountAmount);
		
		return Arrays.asList(new Discount[] {discount});
	}

}
