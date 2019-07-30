package com.shoppingcart.PriceBasket.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.repositories.RateExchangesRepository;
import com.shoppingcart.PriceBasket.services.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private RateExchangesRepository rateExchangesRepository;
	
	@Override
	public Double exchange(Double amount, Integer currencyCodeIn, Integer currencyCodeOut) {

		if (amount == null)
			return null;
		if (currencyCodeIn == null)
			return null;
		if (currencyCodeOut == null)
			return null;
		
		final Double rate =  this.rateExchangesRepository.getRateExange(currencyCodeIn, currencyCodeOut);
		return amount * rate;

	}

}
