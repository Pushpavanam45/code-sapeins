package com.webcrawler.app.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class CrawlResult {
    private String url;
    private String title;
    private String description;
    private int totalLinks;
    private List<Map<String, String>> links;
    private LocalDateTime timestamp;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTotalLinks() { return totalLinks; }
    public void setTotalLinks(int totalLinks) { this.totalLinks = totalLinks; }

    public List<Map<String, String>> getLinks() { return links; }
    public void setLinks(List<Map<String, String>> links) { this.links = links; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
