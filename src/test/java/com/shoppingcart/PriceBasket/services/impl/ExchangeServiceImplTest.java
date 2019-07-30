package com.shoppingcart.PriceBasket.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.shoppingcart.PriceBasket.services.ExchangeService;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeServiceImplTest {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Test
	public void testAmountIsNull() {
		
		Assert.isNull(exchangeService.exchange(null,CurrencyCode.EURO.getCode(),CurrencyCode.EURO.getCode()));
	}
	
	@Test
	public void testCurrencyInIsNull() {
		Assert.isNull(exchangeService.exchange(1.0,null,CurrencyCode.EURO.getCode()));
	}
	
	@Test
	public void testCurrencyOutIsNull() {
		Assert.isNull(exchangeService.exchange(1.0,CurrencyCode.EURO.getCode(),null));
	}

}
