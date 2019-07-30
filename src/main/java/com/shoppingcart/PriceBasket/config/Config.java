package com.shoppingcart.PriceBasket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class Config {
	
	@Value("${pricebasket.currency.default.code}")
	private Integer defaultCurrencyCode;

}
