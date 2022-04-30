package stockanalyzer.ctrl;


import stockanalyzer.jsonDownload.jsontofile;
import stockanalyzer.ui.YahooException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;
import yahooApi.beans.YahooResponse;

import java.io.IOException;
import java.util.*;

public class Controller {

	List<String> Tickers = new ArrayList<>();
	Calendar calendar = null;

	public void process(String ticker) throws YahooException {

		YahooFinance yahooFinance = new YahooFinance();
		List<String> tickers = Arrays.asList(ticker);
		YahooResponse response = yahooFinance.getCurrentData(tickers);
		QuoteResponse quotes = response.getQuoteResponse();
		final String[] longName = new String[1];
		quotes.getResult().stream().forEach(quote -> longName[0] = quote.getLongName());
		quotes.getResult().stream().forEach(quote -> System.out.println(quote.getLongName() + " " + quote.getBid()));


		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, -11);
		System.out.println("Start process");
		Tickers.add(ticker);

		System.out.println("The history:");

		System.out.println("Name: " + longName[0] + ", Last highest price: " + gethighprice(quotes));

		System.out.println( "Avarege price: " + getAveragePrice(quotes));

		System.out.println("The number of records in your shares");

	}

	public double gethighprice(QuoteResponse quotes) throws YahooException {
		double maxPrice = 0;

		try {
			maxPrice = quotes.getResult().stream().map(Result::getBid).max(Double::compare).get();
		} catch (NoSuchElementException e) {
			throw new YahooException("Object didnt found it ");
		}
		return maxPrice;
	}

	public double getAveragePrice(QuoteResponse quotes) throws YahooException {
		double avgPrice = 0;
		try {
			avgPrice = quotes.getResult().stream().mapToDouble(Result::getBid).average().getAsDouble();
		} catch (NoSuchElementException e) {
			throw new YahooException("Object didnt found it ");

		}
		return avgPrice;
	}

	}