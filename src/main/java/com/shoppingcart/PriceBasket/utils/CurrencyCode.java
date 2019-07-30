package com.shoppingcart.PriceBasket.utils;

public enum CurrencyCode {
	
	EURO(0,"€"),POUND(1,"£");
	
	private Integer code;
	private String symbol;
	
	CurrencyCode(final Integer code,final String symbol){
		this.code = code;
		this.symbol=symbol;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public static CurrencyCode fromInteger(final Integer code) {
		switch(code) {
			case 0: return EURO;
			case 1: return POUND;
			default: return null;
		
		}
	}
	
}
