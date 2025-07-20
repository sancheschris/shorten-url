package com.example.shortenurl.repository;

import com.example.shortenurl.model.UrlMapping;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortUrl(String shortUrl);

    // Example: Delete a UrlMapping by its original URL
    void deleteByOriginalUrl(String originalUrl);
}
