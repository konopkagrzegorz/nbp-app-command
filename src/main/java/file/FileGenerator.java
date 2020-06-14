package file;

import parser.CurrencyTable;
import url.UrlGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileGenerator {


    public FileGenerator(UrlGenerator urlGenerator) {
        for (int i = 0; i < urlGenerator.getUrls().size(); i++) {
            try {
                downloadUsingNIO(urlGenerator.getUrls().get(i),"" + i);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
            Scanner scanner = new Scanner("" + 1);
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if( line.startsWith("c") && line.contains(date)) {
                    url = line;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        }
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
