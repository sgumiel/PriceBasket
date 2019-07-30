package com.shoppingcart.PriceBasket.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shoppingcart.PriceBasket.models.db.Item;

import lombok.Data;

@Data
public class Cart implements Serializable {

	private static final long serialVersionUID = 1519075990621304669L;
	
	private List<Item> itemList;
	
	public Cart() {
		this.itemList = new ArrayList<>();
	}
	
	public void addItem(final Item item) {
		this.itemList.add(item);
	}
	
	public int countItems() {
		return this.itemList.size();
	}
	
}
