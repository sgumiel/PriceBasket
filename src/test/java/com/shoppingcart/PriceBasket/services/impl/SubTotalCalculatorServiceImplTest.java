package com.shoppingcart.PriceBasket.services.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubTotalCalculatorServiceImplTest {
	
	@Mock
	private ExchangeServiceImpl exchangeService;
	
	@Mock
	private Cart cart;
	
	@InjectMocks
	private SubTotalCalculatorServiceImpl subTotalCalculatorService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEmptyCart(){
		
		when(cart.getItemList()).thenReturn(new ArrayList<>());
		final Double subTotal = subTotalCalculatorService.calculateSubtotal(cart, CurrencyCode.EURO);
		Assert.assertEquals(0.0,subTotal);
		
	}
	
	@Test
	public void testOneItemInTheCart(){
		
		final List<Item> itemList = new ArrayList();
		itemList.add(new Item("Apples",1.0,CurrencyCode.EURO));
		when(cart.getItemList()).thenReturn(itemList);
		when(exchangeService.exchange(1.0, 0, 0)).thenReturn(1.0);
		final Double subTotal = subTotalCalculatorService.calculateSubtotal(cart, CurrencyCode.EURO);
		Assert.assertEquals(1.0,subTotal);
		
	}
	
	@Test
	public void testSeveralItemsInTheCart(){
		
		final List<Item> itemList = new ArrayList();
		itemList.add(new Item("Apples",1.0,CurrencyCode.EURO));
		itemList.add(new Item("Banana",2.0,CurrencyCode.EURO));
		itemList.add(new Item("Milk",1.5,CurrencyCode.EURO));
		when(cart.getItemList()).thenReturn(itemList);
		when(exchangeService.exchange(1.0, 0, 0)).thenReturn(1.0);
		when(exchangeService.exchange(2.0, 0, 0)).thenReturn(2.0);
		when(exchangeService.exchange(1.5, 0, 0)).thenReturn(1.5);
		final Double subTotal = subTotalCalculatorService.calculateSubtotal(cart, CurrencyCode.EURO);
		Assert.assertEquals(4.5,subTotal);
		
	}

}
