
import model.CurrencyTablePOJO;
import parser.CurrencyTable;
import url.UrlGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws XMLStreamException {
        File xmlFileStartDate = new File("src/currency.xml");
        File xmlSingleCurrency = new File("src/singlecurrency.xml");

        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        XMLStreamReader xsr1 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c073z020415.xml"));
        XMLStreamReader xsr2 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c074z070416.xml"));
        XMLStreamReader xsr3 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c075z070417.xml"));
        XMLStreamReader xsr4 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c076z070418.xml"));
        XMLStreamReader xsr5 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c077z070419.xml"));
        XMLStreamReader xsr6 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c078z070420.xml"));
        XMLStreamReader xsr7 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/c079z070423.xml"));

        String input = "EUR 2013-01-29";
        String currencyType = input.substring(0,3);
        String date = input.substring(4);
        System.out.println(currencyType);
        System.out.println(date);

        LocalDate localDate = LocalDate.parse(date);

        UrlGenerator urlStart = new UrlGenerator(localDate);
        System.out.println(urlStart.getAddress());
        XMLStreamReader start = xif.createXMLStreamReader(new StreamSource(urlStart.getAddress()));
        String url = null;

        try {
            downloadUsingNIO(urlStart.getAddress(), "sitemap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        date = date.replace("-","");
        date = date.substring(2);
        System.out.println(date);

        File file = new File("sitemap.txt");

        try {
            Scanner scanner = new Scanner(file);
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if( line.startsWith("c") && line.contains(date)) {
                    url = line;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (RuntimeException e) {
            System.out.println("No report for that day!");
        }
        System.out.println(url);

        XMLStreamReader xsr8 = xif.createXMLStreamReader(new StreamSource("https://www.nbp.pl/kursy/xml/" + url + ".xml"));



        try {
            JAXBContext context = JAXBContext.newInstance(CurrencyTablePOJO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CurrencyTablePOJO currencyTablePOJO = (CurrencyTablePOJO) unmarshaller.unmarshal(xsr1);
            CurrencyTable currencyTable = new CurrencyTable(currencyTablePOJO);
            System.out.println(currencyTable.toString());

            //Collection<Currency> currencyCollection = currencyTable.getCurrencies();

            //currencyCollection.stream().filter(currency -> currency.getCurrencyCode().equals(CurrencyType.EUR)).forEach(System.out::println);


        } catch (JAXBException e) {
            e.printStackTrace();
        }



    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
