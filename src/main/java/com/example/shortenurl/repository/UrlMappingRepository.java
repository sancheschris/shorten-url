package com.example.shortenurl.repository;

import com.example.shortenurl.model.UrlMapping;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortenUrl(String shortUrl);

    void deleteByLongUrl(String longUrl);
}
