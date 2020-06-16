package file;

import url.UrlGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {

    List<File> fileList;

    public FileGenerator(UrlGenerator urlGenerator) {
        fileList = new ArrayList<>();
        for (int i = 0; i < urlGenerator.getUrls().size(); i++) {
            try {
                downloadUsingNIO(urlGenerator.getUrls().get(i),"file_" + i);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        File file1 = new File(String.valueOf(fos));
        fileList.add(file1);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}