package model;

import java.math.BigDecimal;

public class Currency {

    private CurrencyType currencyType;

    private BigDecimal buyingRate;

    private BigDecimal sellingRate;


    public Currency() {

    }

    public Currency(CurrencyType currencyType, BigDecimal buyingRate, BigDecimal sellingRate) {
        this.currencyType = currencyType;
        this.buyingRate = buyingRate;
        this.sellingRate = sellingRate;
    }

    public Currency (CurrencyPOJO currencyPOJO) {
        this.currencyType = CurrencyType.valueOf(currencyPOJO.getCurrencyCode());
        String buyingRate = currencyPOJO.getBuyingRate().replace(",",".");
        BigDecimal buyingRateDecimal = new BigDecimal(buyingRate);
        this.buyingRate = buyingRateDecimal;
        String sellingRate = currencyPOJO.getBuyingRate().replace(",",".");
        BigDecimal sellingRateDecimal = new BigDecimal(sellingRate);
        this.sellingRate = sellingRateDecimal;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = CurrencyType.valueOf(currencyType);
    }

    public BigDecimal getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        buyingRate = buyingRate.replace(",",".");
        BigDecimal buyingRateDecimal = new BigDecimal(buyingRate);
        this.buyingRate = buyingRateDecimal;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(String sellingRate) {
        sellingRate = sellingRate.replace(",",".");
        BigDecimal sellingRateDecimal = new BigDecimal(sellingRate);
        this.sellingRate = sellingRateDecimal;
    }

    @Override
    public String toString() {
        return currencyType + "\nBuying Rate = " + buyingRate +
                "\nSelling Rate = " + sellingRate;
    }
}
