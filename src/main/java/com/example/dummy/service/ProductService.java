package com.example.dummy.service;

import com.example.dummy.http.HttpClient;
import com.example.dummy.model.Product;
import com.example.dummy.model.ProductListResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final HttpClient client;

    public ProductService(HttpClient client) {
        this.client = client;
    }

    public List<Product> list(int limit, int skip) {
        try {
            String path = String.format("/products?limit=%d&skip=%d", limit, skip);
            Optional<ProductListResponse> opt = client.get(path, ProductListResponse.class);
            return opt.map(ProductListResponse::getProducts).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Product> search(String q) {
        try {
            String path = String.format("/products/search?q=%s", encode(q));
            Optional<ProductListResponse> opt = client.get(path, ProductListResponse.class);
            return opt.map(ProductListResponse::getProducts).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro na busca de produtos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private String encode(String s) {
        return s.replace(" ", "%20");
    }
}
