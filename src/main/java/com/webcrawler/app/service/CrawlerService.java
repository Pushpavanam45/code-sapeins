package com.webcrawler.app.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrawlerService {

    public Map<String, Object> crawl(String url) throws IOException {
        Map<String, Object> result = new HashMap<>();

        Document doc = Jsoup.connect(url).get();

        // Extract title & description
        String title = doc.title();
        String description = Optional.ofNullable(doc.select("meta[name=description]").attr("content"))
                .orElse("No description found");

        // Extract all links
        List<Map<String, String>> links = doc.select("a[href]").stream()
                .map(a -> Map.of("text", a.text(), "href", a.absUrl("href")))
                .limit(50)
                .collect(Collectors.toList());

        result.put("url", url);
        result.put("title", title);
        result.put("description", description);
        result.put("links", links);
        result.put("totalLinks", links.size());
        result.put("timestamp", new Date());

        return result;
    }
}
