import parser.*;

import rate.ExchangeRate;
import url.UrlGenerator;
import url.UrlsGenerator;

public class Main {

    public static void main(String[] args) throws Exception {
        String input = "EUR 2013-01-28 2020-01-31";

        StringGenerator stringGenerator = new StringGenerator(input);

        UrlGenerator urlGenerator = new UrlGenerator(stringGenerator.getFrom(), stringGenerator.getTo());
        UrlsGenerator urlsGenerator = new UrlsGenerator(urlGenerator);

        ExchangeRate exchangeRate = new ExchangeRate();
        XmlParser xmlParser = new XmlParser(exchangeRate, urlsGenerator, stringGenerator.getCurrency());
        System.out.println(exchangeRate.getAverageBuingRate());
        System.out.println(exchangeRate.standardSellingDeviation());

    }
}