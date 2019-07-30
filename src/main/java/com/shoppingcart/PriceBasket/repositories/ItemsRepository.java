package com.shoppingcart.PriceBasket.repositories;

import java.util.List;
import java.util.Optional;

import com.shoppingcart.PriceBasket.models.db.Item;

public interface ItemsRepository {
	
	public List<Item> getAll();
	public Optional<Item> getByDescription(final String description);

}
