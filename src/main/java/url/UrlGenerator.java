package url;

import java.time.LocalDate;

public class UrlGenerator {

    private static final String BASE_DIRECTORY_URL = "https://www.nbp.pl/kursy/xml/dir";
    private static final String BASE_DIRECTORY_URL_TYPE = ".txt";

    private LocalDate date;
    private String address;

    public UrlGenerator(LocalDate date) {
        this.date = date;
        if (date.getYear() == LocalDate.now().getYear()) {
            this.address = BASE_DIRECTORY_URL + BASE_DIRECTORY_URL_TYPE;
        } else {
            this.address = BASE_DIRECTORY_URL + date.getYear() + BASE_DIRECTORY_URL_TYPE;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
