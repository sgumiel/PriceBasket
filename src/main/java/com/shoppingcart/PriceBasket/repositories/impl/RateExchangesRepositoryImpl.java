package com.shoppingcart.PriceBasket.repositories.impl;

import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.repositories.RateExchangesRepository;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@Service
public class RateExchangesRepositoryImpl implements RateExchangesRepository {

	private static Double[][] rateExangeTable;
	
	static {
		rateExangeTable = createRateExchangeTable();
	}
	@Override
	public Double getRateExange(Integer currencyCodeIn, Integer currencyCodeOut) {
		
		if(currencyCodeIn >= rateExangeTable.length) return null;
		if(currencyCodeOut >= rateExangeTable[currencyCodeIn].length) return null;
		
		return rateExangeTable[currencyCodeIn][currencyCodeOut];
	}
	
	private static Double[][] createRateExchangeTable(){
		
		rateExangeTable = new Double[2][2];
		rateExangeTable[CurrencyCode.EURO.getCode()][CurrencyCode.EURO.getCode()]=1.0;
		rateExangeTable[CurrencyCode.EURO.getCode()][CurrencyCode.POUND.getCode()]=0.9;
		rateExangeTable[CurrencyCode.POUND.getCode()][CurrencyCode.EURO.getCode()]=1/0.9;
		rateExangeTable[CurrencyCode.POUND.getCode()][CurrencyCode.POUND.getCode()]=1.0;
		
		return rateExangeTable;
	}

}
