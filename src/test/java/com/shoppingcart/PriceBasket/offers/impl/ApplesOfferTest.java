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
public class ApplesOfferTest {
	
	@Autowired
	private ApplesOffer applesOffer;
	
	@Test
	public void testNoApplesOfferAvailable() {
		
		final Cart cart = mock(Cart.class);
		
		// No Apples in the cart
		when(cart.getItemList()).thenReturn(new ArrayList<>());
		
		final List<Discount> discountsList = this.applesOffer.applyOffer(cart);
		
		Assert.assertEquals(0, discountsList.size());
		
	}
	
	@Test
	public void testApplesOfferAvailable() {
		
		final Cart cart = mock(Cart.class);
		
		// Apples in the cart
		final List<Item> itemList = (List)Arrays.asList(
			new Item[] { new Item("Apples", 1.00, CurrencyCode.EURO),
		});
		
		when(cart.getItemList()).thenReturn(itemList);
		
		final List<Discount> discountsList = this.applesOffer.applyOffer(cart);
		
		Assert.assertEquals(1, discountsList.size());
		Assert.assertEquals(Double.doubleToLongBits(0.1), Double.doubleToLongBits(discountsList.get(0).getAmount()));
		
		
	}

}
