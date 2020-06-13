package parser;

import model.Currency;
import model.CurrencyPOJO;
import model.CurrencyTablePOJO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyTable {

    private LocalDate date;
    private List<Currency> currencies;

    public CurrencyTable() {

    }

    public CurrencyTable(CurrencyTablePOJO currencyTablePOJO) {
        this.currencies = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currencyTablePOJO.getPublicationDate());
        this.date = localDate;
        for (CurrencyPOJO currencyPOJO : currencyTablePOJO.getCurrencies()) {
            Currency currency = new Currency(currencyPOJO);
            this.currencies.add(currency);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date);
        localDate.parse(date,formatter);
        this.date = localDate;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return date + "\n" + printCurrencies();
    }

    private String printCurrencies() {
        StringBuilder result = new StringBuilder();
        for (Currency currency : currencies) {
            result.append(currency.getCurrencyType() + "\n Buying Rate = " + currency.getBuyingRate() + " / Selling Rate = " + currency.getSellingRate() + "\n");
        }
        return  result.toString();
    }
}
