package com.shoppingcart.PriceBasket.offers.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Soup2TinsBreadHalfPriceOfferTest {
	
	@Autowired
	private Soup2TinsBreadHalfPriceOffer soup2TinsBreadHalfPriceOffer;
	
	@Test
	public void testNoSoupItemNoDiscount() {
		
		final Cart cart = mock(Cart.class);
		
		// No Soup in the cart
		when(cart.getItemList()).thenReturn(new ArrayList<>());
		
		final List<Discount> discountsList = this.soup2TinsBreadHalfPriceOffer.applyOffer(cart);
		
		Assert.assertEquals(0, discountsList.size());
		
	}
	
	@Test
	public void test1SoupItemNoDiscount() {
		
		final Cart cart = mock(Cart.class);
		
		final List<Item> itemList = (List)Arrays.asList(
			new Item[] { new Item("Soup", 1.00, CurrencyCode.EURO),
		});
		
		when(cart.getItemList()).thenReturn(itemList);
		
		final List<Discount> discountsList = this.soup2TinsBreadHalfPriceOffer.applyOffer(cart);
		
		Assert.assertEquals(0, discountsList.size());
		
	}
	
	@Test
	public void test2SoupItemButNoBreadNoDiscount() {
		
		final Cart cart = mock(Cart.class);
		
		final List<Item> itemList = (List)Arrays.asList(
			new Item[] { new Item("Soup", 1.00, CurrencyCode.EURO),
			new Item("Soup", 1.00, CurrencyCode.EURO)
		});
		
		when(cart.getItemList()).thenReturn(itemList);
		
		final List<Discount> discountsList = this.soup2TinsBreadHalfPriceOffer.applyOffer(cart);
		
		Assert.assertEquals(0, discountsList.size());
		
	}
	
	@Test
	public void test2SoupItem1BreadDiscount() {
		
		final Cart cart = mock(Cart.class);
		
		final List<Item> itemList = (List)Arrays.asList(
			new Item[] { new Item("Soup", 1.00, CurrencyCode.EURO),
			new Item("Soup", 1.00, CurrencyCode.EURO),
			new Item("Bread", 2.00, CurrencyCode.EURO)
		});
		
		when(cart.getItemList()).thenReturn(itemList);
		
		final List<Discount> discountsList = this.soup2TinsBreadHalfPriceOffer.applyOffer(cart);
		
		Assert.assertEquals(Double.doubleToLongBits(1.0), Double.doubleToLongBits(discountsList.get(0).getAmount()));
		
	}
	

}
