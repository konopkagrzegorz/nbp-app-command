package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyTablePOJO {

    @XmlElement(name = "data_publikacji")
    private String publicationDate;

    @XmlElement(name = "pozycja")
    private List<CurrencyPOJO> currencies = new ArrayList<>();

    public CurrencyTablePOJO() {

    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<CurrencyPOJO> getCurrencies() { return currencies;
    }

    public void setCurrencies(List<CurrencyPOJO> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "CurrencyTablePOJO{" +
                "publicationDate='" + publicationDate + '\'' +
                ", currencies=" + currencies +
                '}';
    }
}
