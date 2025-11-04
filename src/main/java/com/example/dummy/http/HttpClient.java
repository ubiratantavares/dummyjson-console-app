package com.example.dummy.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

/**
 * Utility wrapper around java.net.http.HttpClient for GET/POST/PUT/DELETE.
 * Serializa/deserializa JSON via Jackson ObjectMapper.
 */

public class HttpClient {
    private final java.net.http.HttpClient client;
    @Getter
    private final ObjectMapper mapper;
    private final String baseUrl;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.client = java.net.http.HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.mapper = new ObjectMapper();
    }

    public <T> Optional<T> get(String path, Class<T> responseType)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (is2xx(response.statusCode())) {
            return Optional.of(mapper.readValue(response.body(), responseType));
        } else {
            System.err.printf("GET %s -> HTTP %d: %s%n", path, response.statusCode(), response.body());
            return Optional.empty();
        }
    }

    public <T, R> Optional<R> post(String path, T bodyObj, Class<R> responseType)
            throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(bodyObj);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (is2xx(response.statusCode())) {
            return Optional.of(mapper.readValue(response.body(), responseType));
        } else {
            System.err.printf("POST %s -> HTTP %d: %s%n", path, response.statusCode(), response.body());
            return Optional.empty();
        }
    }

    public <T, R> Optional<R> put(String path, T bodyObj, Class<R> responseType)
            throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(bodyObj);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (is2xx(response.statusCode())) {
            return Optional.of(mapper.readValue(response.body(), responseType));
        } else {
            System.err.printf("PUT %s -> HTTP %d: %s%n", path, response.statusCode(), response.body());
            return Optional.empty();
        }
    }

    public boolean delete(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .DELETE()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (is2xx(response.statusCode())) {
            return true;
        } else {
            System.err.printf("DELETE %s -> HTTP %d: %s%n", path, response.statusCode(), response.body());
            return false;
        }
    }

    private boolean is2xx(int status) {
        return status >= 200 && status < 300;
    }
}
