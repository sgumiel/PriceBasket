package com.shoppingcart.PriceBasket.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.services.ExchangeService;
import com.shoppingcart.PriceBasket.services.SubTotalCalculatorService;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@Service
public class SubTotalCalculatorServiceImpl implements SubTotalCalculatorService {

	@Autowired
	private ExchangeService exchangeService;
	
	@Override
	public Double calculateSubtotal(Cart cart, CurrencyCode currencyCode) {
		
		if(cart==null) return 0.0;
		if(currencyCode==null) return 0.0;
		
		final Optional<Double> optSubtotal = cart.getItemList().stream().map( item -> {
			
			final Double price = item.getPrice();
			final Integer currencyCodeItem = item.getCurrency().getCode();
			final Double priceInEuros = exchangeService.exchange(price, currencyCodeItem, currencyCode.getCode());
			
			return priceInEuros;
			
		}).reduce(Double::sum);
		
		return optSubtotal.orElse(0.0);
	}

}
