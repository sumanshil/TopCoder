package com.lambdathrowingex;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class WebCrawler {


    public static void main(String[] args) {

        List<String> urlsTo = Arrays.asList("https://masterdevskills.com");

        WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl(urlsTo);
    }

    private void crawl(List<String> urlsToCrawl) {
        urlsToCrawl.stream().map(ThrowingFunction.unchecked(urlToCrawl -> new URL(urlToCrawl))).forEach(this::save);
    }

    private URL createURL(String urlToCrawl) {
        try {
            return new URL(urlToCrawl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void save(URL url) {
        try {
            String uuid = UUID.randomUUID().toString();
            InputStream inputStream = url.openConnection().getInputStream();
            Files.copy(inputStream, Paths.get(uuid + ".txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
