package com.shoppingcart.PriceBasket.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.repositories.ItemsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartShoppingFillerServiceImplTest {
	
	@Mock
	private ItemsRepository itemsRepository;
	
	@InjectMocks
	private CartShoppingFillerServiceImpl cartShoppingFillerService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFillCartWithNullArgs() {
		
		final String[] args = new String[] {};
		final Cart cart = cartShoppingFillerService.fillCart(null);
		
		assertEquals(0, cart.getItemList().size());
		
	}
	
	@Test
	public void testFillCartWithEmptyList() {
		
		final String[] args = new String[] {};
		final Cart cart = cartShoppingFillerService.fillCart(args);
		
		assertEquals(args.length, cart.getItemList().size());
		
	}
	
	@Test
	public void testFillCartWithAnItem() {
		
		final String[] args = new String[] {
				"Apples"
		};
		
		final Optional<Item> opItem = Optional.of(new Item());
		when(itemsRepository.getByDescription(Matchers.anyString())).thenReturn(opItem);
		final Cart cart = cartShoppingFillerService.fillCart(args);
		
		assertEquals(args.length, cart.getItemList().size());
		
	}

}
