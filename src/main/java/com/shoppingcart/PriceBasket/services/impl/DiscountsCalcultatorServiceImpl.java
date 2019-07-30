package com.shoppingcart.PriceBasket.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.Discount;
import com.shoppingcart.PriceBasket.offers.Offer;
import com.shoppingcart.PriceBasket.offers.impl.ApplesOffer;
import com.shoppingcart.PriceBasket.offers.impl.Soup2TinsBreadHalfPriceOffer;
import com.shoppingcart.PriceBasket.services.DiscountsCalcultatorService;

@Service
public class DiscountsCalcultatorServiceImpl implements DiscountsCalcultatorService,ApplicationContextAware {
	
	private static List<Offer> offersList;

	@Override
	public List<Discount> calculateDiscounts(Cart cart) {
		
		List<Discount> discountsList = new ArrayList<Discount>();
		
		offersList.stream().forEach( offer -> discountsList.addAll(offer.applyOffer(cart)));
		
		return discountsList;
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		offersList = Arrays.asList( new Offer[] {
				applicationContext.getBean(ApplesOffer.class),
				applicationContext.getBean(Soup2TinsBreadHalfPriceOffer.class)
		});
	}

}

