package 第六章.六点一收集器简介;

import common.Dish;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zc
 * @create 2020-12-30 20:18
 **/
public class Main {

	public static void main(String[] args) {
		List<Transaction> transactions = new ArrayList<>();
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		for (Transaction transaction : transactions) {
			Currency currency = transaction.getCurrency();
			List<Transaction> transactionsForCurrency =
					transactionsByCurrencies.get(currency);
			if (transactionsForCurrency == null) {
				transactionsForCurrency = new ArrayList<>();
				transactionsByCurrencies
						.put(currency, transactionsForCurrency);
			}
			transactionsForCurrency.add(transaction);
		}

		Map<Currency, List<Transaction>> transactionsByCurrencies1 =
				transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
	}
}
