package com.shoppingcart.PriceBasket.services.impl;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shoppingcart.PriceBasket.models.SummarizePay;
import com.shoppingcart.PriceBasket.services.SummarizePayPrinterService;

@Service
public class SummarizePayPrinterServiceImpl implements SummarizePayPrinterService{

	private final String LINE_FORMAT = "%50s : %10s %s %30s %n";
	private final DecimalFormat df = new DecimalFormat("####0.00 ");
	
	@Override
	public String createTextOfSummarizePay(final SummarizePay summarizePay) {
		
		final String subTotalLine = createSubTotalLine(summarizePay);//subTotalFormatted,symbol,summarizePay.getDiscountsList().size()!=0);
		final String discountsLines = createDiscountsLines(summarizePay);
		final String totalLine = createTotalLine(summarizePay);
		
		return new StringBuffer().append(subTotalLine).append(discountsLines).append(totalLine).toString();
		
	}
	
	private String createTotalLine(SummarizePay summarizePay) {
		
		final String totalFormatted = df.format(summarizePay.getTotal());
		
		final boolean offersApplied = summarizePay.getDiscountsList().size()!=0;
		String totalText = "Total";
		
		if(!offersApplied) {
			totalText = "Total Price";
			
		}
		return String.format(LINE_FORMAT,
				StringUtils.right(totalText,50),
				StringUtils.right(""+totalFormatted, 10),
				summarizePay.getCurrencyCode().getSymbol(),
				"");
		
	}

	private String createDiscountsLines(final SummarizePay summarizePay) {
		
		final StringBuffer discountsLinesBuffer = new StringBuffer();
		
		summarizePay.getDiscountsList().stream().forEach( discount -> {
			final String discountFormatted = df.format(discount.getAmount()*-1);
			discountsLinesBuffer.append(String.format(LINE_FORMAT,
				StringUtils.right(discount.getDescription(),50),
				StringUtils.right(""+discountFormatted, 10),
				summarizePay.getCurrencyCode().getSymbol(),
				""));
			
		});
		
		
		return discountsLinesBuffer.toString();
		
	}

	private String createSubTotalLine(final SummarizePay summarizePay) {
		
		final String subTotalFormatted = df.format(summarizePay.getSubtotal());
		
		final boolean offersApplied = summarizePay.getDiscountsList().size()!=0;
		String noOffersApplied = "";
		
		if(!offersApplied) {
			noOffersApplied = "( No offers available )";
		}
		return String.format(LINE_FORMAT,
				StringUtils.right("Subtotal",50),
				StringUtils.right(""+subTotalFormatted, 10),
				summarizePay.getCurrencyCode().getSymbol(),
				noOffersApplied);
		
	}

}
