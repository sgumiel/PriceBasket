package com.shoppingcart.PriceBasket.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.repositories.ItemsRepository;
import com.shoppingcart.PriceBasket.services.CartShoppingFillerService;

@Service
public class CartShoppingFillerServiceImpl implements CartShoppingFillerService {

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Override
	public Cart fillCart(String[] args) {
		
		if(args==null) return new Cart();
		
		final Cart cart = new Cart(); // empty cart
		
		Arrays.asList(args).stream().forEach(itemString -> {
			final Optional<Item> opItem = itemsRepository.getByDescription(itemString);
			if(opItem.isPresent()) {
				cart.addItem(opItem.get());
			}
		});
		
		return cart;
	}
}
