package url;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UrlGenerator {

    private static final String BASE_DIRECTORY_URL = "https://www.nbp.pl/kursy/xml/dir";
    private static final String BASE_DIRECTORY_URL_TYPE = ".txt";

    private List<String> urls;

    public UrlGenerator(String dataFrom, String dataTo) throws Exception {
        this.urls = new ArrayList<>();
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(dataFrom);
            to = LocalDate.parse(dataTo);
        } catch (Exception ex) {
            throw new Exception("Wrong data input!");
        }
        if (from.compareTo(to) > 0) {
            throw new Exception("Data 'from' is younger than 'to'");
        } else if (to.getYear() - from.getYear() >= 0) {
            for (int i = from.getYear(); i <= to.getYear(); i++) {
                if (i != LocalDate.now().getYear()) {
                    urls.add(BASE_DIRECTORY_URL + i + BASE_DIRECTORY_URL_TYPE);
                } else if (i == LocalDate.now().getYear()) {
                    urls.add(BASE_DIRECTORY_URL + BASE_DIRECTORY_URL_TYPE);
                }
            }
        }
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}