package com.shoppingcart.PriceBasket.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.models.SummarizePay;
import com.shoppingcart.PriceBasket.services.DiscountsCalcultatorService;
import com.shoppingcart.PriceBasket.services.SubTotalCalculatorService;
import com.shoppingcart.PriceBasket.services.SummarizePayService;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@Service
public class SummarizePayServiceImpl implements SummarizePayService{

	@Autowired
	private DiscountsCalcultatorService discountsCalculatorService;
	
	@Autowired
	private SubTotalCalculatorService subTotalCalculatorService;
	
	@Override
	public SummarizePay createSummarizePay(Cart cart, CurrencyCode currencyCode) {
		
		final Double subTotal = subTotalCalculatorService.calculateSubtotal(cart,currencyCode);
		final List<Discount> discountsList =  discountsCalculatorService.calculateDiscounts(cart);
		final SummarizePay summarizePay = new SummarizePay(subTotal,discountsList,currencyCode);
		
		return summarizePay;
		
	}
	
}
