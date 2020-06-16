package url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UrlsGenerator {

    private static final String BASE_DIRECTORY_URL = "http://www.nbp.pl/kursy/xml/";
    private static final String BASE_DIRECTORY_URL_TYPE = ".xml";

    private List<String> urlsList;
    private UrlsGenerator urlGenerator;
    private Integer from;
    private Integer to;

    public UrlsGenerator(UrlGenerator urlGenerator){
        this.urlsList = new ArrayList<>();
        String from = urlGenerator.getFrom().toString().replace("-","");
        String to = urlGenerator.getTo().toString().replace("-","");
        from = from.substring(2);
        to = to.substring(2);
        this.from = Integer.parseInt(from);
        this.to = Integer.parseInt(to);

        for (int i = 0; i <urlGenerator.getUrls().size(); i++) {
            try {
                URL oracle = new URL(urlGenerator.getUrls().get(i));
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String inputLine;
                String helper;
                long fromToLong = Long.parseLong(urlGenerator.getFrom().toString().replace("-",""));
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.startsWith("c")) {
                        helper = inputLine.substring(5);
                        if ((Integer.parseInt(helper) >= this.from) && (Integer.parseInt(helper) <= this.to)) {
                            this.urlsList.add(BASE_DIRECTORY_URL + inputLine + BASE_DIRECTORY_URL_TYPE);
                            System.out.println(BASE_DIRECTORY_URL + inputLine + BASE_DIRECTORY_URL_TYPE);
                        }
                    }
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getUrlsList() {
        return urlsList;
    }

    public void setUrlsList(List<String> urlsList) {
        this.urlsList = urlsList;
    }
}
