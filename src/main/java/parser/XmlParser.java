package parser;

import model.CurrencyTablePOJO;
import rate.ExchangeRate;
import url.UrlsGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class XmlParser {

    private UrlsGenerator urlsGenerator;
    private XMLInputFactory xif = XMLInputFactory.newFactory();
    private ExchangeRate exchangeRate;


    public XmlParser(ExchangeRate exchangeRate, UrlsGenerator urlsGenerator, String currencyCode) throws Exception {
        this.urlsGenerator = urlsGenerator;
        this.exchangeRate = exchangeRate;
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        if (currencyCode.equals("EUR") || currencyCode.equals("USD") || currencyCode.equals("GBP")
         || currencyCode.equals("CHF")) {
            for (int i = 0; i < urlsGenerator.getUrlsList().size(); i++) {
                try {
                    JAXBContext context = JAXBContext.newInstance(CurrencyTablePOJO.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    XMLStreamReader temp = xif.createXMLStreamReader(new StreamSource(urlsGenerator.getUrlsList().get(i)));
                    CurrencyTablePOJO currencyTablePOJO = (CurrencyTablePOJO) unmarshaller.unmarshal(temp);
                    CurrencyTable currencyTable = new CurrencyTable(currencyTablePOJO);
                    for (int j = 0; j < currencyTable.getCurrencies().size(); j++) {
                        Currency tempCurrency = currencyTable.getCurrencies().get(j);
                        if (tempCurrency.getCurrencyType().equals(CurrencyType.valueOf(currencyCode))) {
                            exchangeRate.addCurrency(tempCurrency);
                            //System.out.println(tempCurrency);
                        }
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                } catch (XMLStreamException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            throw new Exception(currencyCode + " is not supported!");
        }
    }
}
