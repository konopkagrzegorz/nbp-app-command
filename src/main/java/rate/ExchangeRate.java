package rate;

import parser.Currency;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRate {

    private List<Currency> currencies = new ArrayList<>();

    public ExchangeRate() {

    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void addCurrency(Currency currency) {
        this.currencies.add(currency);
    }

    public BigDecimal getAverageBuingRate() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Currency currency : currencies) {
            sum = sum.add(currency.getBuyingRate());
        }
        BigDecimal size = new BigDecimal(Integer.toString(currencies.size()));
        BigDecimal avg = sum.divide(size, 4, RoundingMode.HALF_UP);
        return avg;
    }

    private BigDecimal getAverageSellingRate() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Currency currency : currencies) {
            sum = sum.add(currency.getSellingRate());
        }
        BigDecimal size = new BigDecimal(Integer.toString(currencies.size()));
        BigDecimal avg = sum.divide(size, 4, RoundingMode.HALF_UP);
        return avg;
    }

    public BigDecimal standardSellingDeviation() {
        BigDecimal avg = getAverageSellingRate();
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal size = new BigDecimal(Integer.toString(currencies.size()));
        for (int i = 0; i < currencies.size(); i++) {
            BigDecimal square = currencies.get(i).getSellingRate();
            square = square.subtract(avg);
            square = square.pow(2);
            sum = sum.add(square);

//            sum = sum.add(BigDecimal.valueOf
//                    (Math.pow(currencies.get(i).getSellingRate().doubleValue() - avg.doubleValue(),2)));
//        }
//        BigDecimal deviation = BigDecimal.valueOf(Math.pow((sum.doubleValue()/size.doubleValue()),2));
        }
        BigDecimal deviation = sum.divide(size,20,RoundingMode.HALF_UP);
        deviation = deviation.sqrt(new MathContext(3));
        return deviation;
    }
}
