package model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CurrencyPOJO {

    private String currencyCode;

    private String buyingRate;

    private String sellingRate;

    public CurrencyPOJO() {

    }

    @XmlElement(name = "kod_waluty")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @XmlElement(name = "kurs_kupna")
    public String getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        this.buyingRate = buyingRate;
    }

    @XmlElement(name = "kurs_sprzedazy")
    public String getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(String sellingRate) {
        this.sellingRate = sellingRate;
    }

    @Override
    public String toString() {
        return currencyCode +
                ", buyingRate=" + buyingRate +
                ", sellingRate=" + sellingRate;
    }
}
