package com.example.shortenurl.controller;

import com.example.shortenurl.service.UrlShorteningService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlShorteningController {

    @Autowired
    private UrlShorteningService service;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String longUrl) {
        return service.shortenUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        String longUrl = service.getLongUrl(shortUrl);
        return new RedirectView(Objects.requireNonNullElse(longUrl, "/not-found"));
    }
}
