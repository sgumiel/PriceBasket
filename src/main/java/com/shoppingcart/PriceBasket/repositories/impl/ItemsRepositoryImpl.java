package com.shoppingcart.PriceBasket.repositories.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.db.Item;
import com.shoppingcart.PriceBasket.repositories.ItemsRepository;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@Service
public class ItemsRepositoryImpl implements ItemsRepository {

	private static List<Item> itemsList;
	
	static {
		itemsList = createItems();
	}
	
	@Override
	public List<Item> getAll() {
		return itemsList;
	}
	
	@Override
	public Optional<Item> getByDescription(String description) {
		final List<Item> itemList = itemsList.stream().filter( item -> item.getDescription().equals(description)).collect(Collectors.toList());
		if(itemList.size()==1) return Optional.of(itemList.get(0));
		return Optional.empty();
	}

	private static List<Item> createItems() {

		return Arrays.asList(
				new Item[] { new Item("Soup", 0.65, CurrencyCode.POUND), 
				new Item("Bread", 0.80, CurrencyCode.POUND),
				new Item("Milk", 1.30, CurrencyCode.POUND), 
				new Item("Apples", 1.00, CurrencyCode.POUND),
				new Item("Peanuts", 2.00, CurrencyCode.EURO), 
				new Item("Banana", 1.20, CurrencyCode.EURO), });
	}

	
}