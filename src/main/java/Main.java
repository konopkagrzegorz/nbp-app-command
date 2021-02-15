import parser.*;

import rate.ExchangeRate;
import url.UrlGenerator;
import url.UrlsGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner reader = new Scanner(System.in);
        System.out.println("choose currency code and dates");
        System.out.println("example input EUR 2013-01-28 2013-01-31");
        String input = reader.nextLine();
        StringGenerator stringGenerator = new StringGenerator(input);

        UrlGenerator urlGenerator = new UrlGenerator(stringGenerator.getFrom(), stringGenerator.getTo());
        UrlsGenerator urlsGenerator = new UrlsGenerator(urlGenerator);

        ExchangeRate exchangeRate = new ExchangeRate();
        XmlParser xmlParser = new XmlParser(exchangeRate, urlsGenerator, stringGenerator.getCurrency());
        System.out.println(String.format("Average buying rate: %.4f", exchangeRate.getAverageBuingRate()));
        System.out.println(String.format("Standard selling deviation: %.4f", exchangeRate.standardSellingDeviation()));

    }
}