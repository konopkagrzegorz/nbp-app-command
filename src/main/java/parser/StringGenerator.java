package parser;

public class StringGenerator {

    private String currency;

    private String from;

    private String to;

    private String input;

    public StringGenerator(String input) {

        this.input = input;
        this.currency = input.substring(0,3);
        this.from = input.substring(4,14);
        this.to = input.substring(15);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
