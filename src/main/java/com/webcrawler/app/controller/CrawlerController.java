package com.webcrawler.app.controller;

import com.webcrawler.app.service.CrawlerService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000") // React frontend
@RestController
@RequestMapping("/api")
public class CrawlerController {

    private final CrawlerService crawlerService;

    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @PostMapping("/crawl")
    public Map<String, Object> crawl(@RequestBody Map<String, String> body) throws IOException {
        String url = body.get("url");
        return crawlerService.crawl(url);
    }
}
