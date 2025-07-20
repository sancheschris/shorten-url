package com.example.shortenurl.service;

import com.example.shortenurl.model.UrlMapping;
import com.example.shortenurl.repository.UrlMappingRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class UrlShorteningService {

    private final UrlMappingRepository urlMappingRepository;
    private static final String BASE_URL = "http://short.url/";

    public UrlShorteningService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String shortenUrl(String longUrl) {
        Optional<UrlMapping> shortUrl = urlMappingRepository.findByShortenUrl(longUrl);

        if (shortUrl.isPresent()) {
            return BASE_URL + shortUrl.get().getShortenUrl();
        }
        String shortUrlCode = generateShortUrlCode();

        UrlMapping mapping = new UrlMapping();
        mapping.setShortenUrl(shortUrlCode);
        mapping.setLongUrl(longUrl);
        mapping.setCreatedAt(LocalDateTime.now());

        urlMappingRepository.save(mapping);
        return BASE_URL + shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        Optional<UrlMapping> mapping = urlMappingRepository.findByShortenUrl(shortUrl);
        return mapping.map(UrlMapping::getLongUrl).orElse(null);
    }

    public String generateShortUrlCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }
}
