package com.shoppingcart.PriceBasket.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.PriceBasket.models.db.Item;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

	private Cart cart;

	@Before
	public void createEmptyCart() {
		this.cart = new Cart();

	}
	
	@Test
	public void whenIAddAnItemTheCountIsPreviousCountPlus() {
		
		final int countBefore = this.cart.countItems();
		
		this.cart.addItem(new Item());
		
		final int countAfter = this.cart.countItems();
		
		Assert.assertEquals(countBefore+1, countAfter);
	}

}
