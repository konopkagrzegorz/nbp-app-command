import file.FileGenerator;
import parser.StringGenerator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combiner {

    private String fromFile;

    private String toFile;

    private List<String> urlCurrencies;

    public Combiner(FileGenerator file, StringGenerator stringGenerator) throws FileNotFoundException {

        this.urlCurrencies = new ArrayList<>();
        this.fromFile = stringGenerator.getFrom().replace("-","");
        this.toFile = stringGenerator.getTo().replace("-","");
        this.fromFile = fromFile.substring(2);
        this.toFile = toFile.substring(2);
        System.out.println(file.getFileList().toString());
        int from = Integer.parseInt(fromFile);
        int to = Integer.parseInt(toFile);

        for (int i = 0; i < file.getFileList().size(); i++) {
            Scanner scanner = new Scanner(file.getFileList().get(i));
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (line.startsWith("c") && Integer.parseInt(line) >= from && Integer.parseInt(line) <= to ) {
                    urlCurrencies.add(line);
                }
            }
        }
    }

    public List<String> getUrlCurrencies() {
        return urlCurrencies;
    }

    public void setUrlCurrencies(List<String> urlCurrencies) {
        this.urlCurrencies = urlCurrencies;
    }
}
