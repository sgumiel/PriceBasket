package com.shoppingcart.PriceBasket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shoppingcart.PriceBasket.config.Config;
import com.shoppingcart.PriceBasket.models.Cart;
import com.shoppingcart.PriceBasket.models.SummarizePay;
import com.shoppingcart.PriceBasket.services.CartShoppingFillerService;
import com.shoppingcart.PriceBasket.services.SummarizePayPrinterService;
import com.shoppingcart.PriceBasket.services.SummarizePayService;
import com.shoppingcart.PriceBasket.utils.CurrencyCode;

@SpringBootApplication
public class PriceBasketApplication {

	private static Logger logger = LoggerFactory.getLogger(PriceBasketApplication.class);

	private static CartShoppingFillerService cartShoppingFillerService;
	private static SummarizePayService summarizePayService;
	private static SummarizePayPrinterService summarizePayPrinterService;
	private static Config config;

	public static void main(String[] args) {

		SpringApplication.run(PriceBasketApplication.class, args);
		initCartBasket(args);

	}

	private static void initCartBasket(final String[] args) {
		logger.debug("Init initCartBasket");

		final Cart cart = cartShoppingFillerService.fillCart(args);
		final SummarizePay summarizePay = summarizePayService.createSummarizePay(cart, CurrencyCode.fromInteger(config.getDefaultCurrencyCode()));
		final String summarizePayText = summarizePayPrinterService.createTextOfSummarizePay(summarizePay);
		
		logger.debug("\n{}",summarizePayText);
		logger.debug("End initCartBasket");
	}

	@Autowired
	public void setCartShoppingFillerService(CartShoppingFillerService cartShoppingFillerService) {
		this.cartShoppingFillerService = cartShoppingFillerService;
	}
	
	@Autowired
	public void setSummarizePayService(SummarizePayService summarizePayService) {
		this.summarizePayService = summarizePayService;
	}
	
	@Autowired
	public void setSummarizePayPrinterService(SummarizePayPrinterService summarizePayPrinterService) {
		this.summarizePayPrinterService = summarizePayPrinterService;
	}
	
	@Autowired
	public void setConfig(Config config) {
		this.config = config;
	}

}
