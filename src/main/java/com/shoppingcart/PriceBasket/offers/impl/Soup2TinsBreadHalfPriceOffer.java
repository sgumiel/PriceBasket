package com.shoppingcart.PriceBasket.offers.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.config.Config;
import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.offers.Offer;
import com.shoppingcart.PriceBasket.services.ExchangeService;

@Service
public class Soup2TinsBreadHalfPriceOffer implements Offer {

	private Double rateDiscount = 0.5;
	
	@Autowired
	private Config config;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Override
	public List<Discount> applyOffer(Cart cart) {
		
		final long tinsOfSoup = cart.getItemList().stream().filter( item -> item.getDescription().equals("Soup")).count();
		final long loafsOfBread = cart.getItemList().stream().filter( item -> item.getDescription().equals("Bread")).count();
		
		final long maxDiscontsPossibles = tinsOfSoup/2;
		
		long realDiscounts = 0;
		if(maxDiscontsPossibles>loafsOfBread) {
			realDiscounts=loafsOfBread;
		}else {
			realDiscounts=maxDiscontsPossibles;
		}
		
		if(realDiscounts>0) {
			return createDiscounts(cart,realDiscounts);
		}
		
		return new ArrayList<>();
		
		
	}

	private List<Discount> createDiscounts(Cart cart, long realDiscounts) {
		final Item anyItemOfBread = cart.getItemList().stream().filter( item -> item.getDescription().equals("Bread")).findAny().get();
		final Double breadPrice = anyItemOfBread.getPrice();
		final Double priceInEuros = exchangeService.exchange(breadPrice, anyItemOfBread.getCurrency().getCode(), config.getDefaultCurrencyCode());
		
		return Arrays.asList(new Discount[] {
				new Discount("Half Price Bread", priceInEuros * realDiscounts * rateDiscount)
				
		});
	}

}
