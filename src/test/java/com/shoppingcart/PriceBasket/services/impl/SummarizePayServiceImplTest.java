package com.shoppingcart.PriceBasket.services.impl;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.models.SummarizePay;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummarizePayServiceImplTest {

	@Mock
	private DiscountsCalcultatorServiceImpl discountsCalculatorService;
	
	@Mock
	private SubTotalCalculatorServiceImpl subTotalCalculatorService;
	
	@InjectMocks
	private SummarizePayServiceImpl summarizePayService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		
		final Double SUBTOTAL = 17.0;
		final Double DISCOUNT_1 = 1.0;
		final Double DISCOUNT_2 = 3.0;
		
		List<Discount> discountsList = Arrays.asList(new Discount[] {
			new Discount("item test 1",DISCOUNT_1),	
			new Discount("item test 2",DISCOUNT_2)
		});
		when(subTotalCalculatorService.calculateSubtotal(new Cart(), CurrencyCode.EURO)).thenReturn(SUBTOTAL);
		when(discountsCalculatorService.calculateDiscounts(new Cart())).thenReturn(discountsList);
		
		final SummarizePay summarizePay = summarizePayService.createSummarizePay(new Cart(), CurrencyCode.EURO);
		Assert.assertEquals(SUBTOTAL, summarizePay.getSubtotal());
		Assert.assertEquals(SUBTOTAL-DISCOUNT_1-DISCOUNT_2, summarizePay.getTotal());
	}
}
