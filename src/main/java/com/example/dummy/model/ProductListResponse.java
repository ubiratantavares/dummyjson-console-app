package com.example.dummy.model;

import lombok.Data;

import java.util.List;

/**
 * Wrapper response for /products endpoints
 */
@Data
public class ProductListResponse {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}
