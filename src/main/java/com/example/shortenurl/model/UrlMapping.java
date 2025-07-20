package com.example.shortenurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class UrlMapping {
    @Id
    private String shortenUrl;
    private String longUrl;
    private LocalDateTime createdAt;
}
