package com.example.dummy.controller;

import com.example.dummy.menu.Menu;
import com.example.dummy.menu.ProductMenu;
import com.example.dummy.model.Product;
import com.example.dummy.service.ProductService;

import java.util.List;

public class ProductController implements Controller {

    private final ProductService productService;
    private final Menu menu;

    public ProductController(ProductService productService) {
        this.menu = new ProductMenu();
        this.productService = productService;
    }

    @Override
    public void execute() {
        while (true) {
            menu.print();
            String opt = menu.getScanner().nextLine().trim();
            switch (opt) {
                case "1" -> listProducts();
                case "2" -> searchProducts();
                case "3" -> {return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listProducts() {
        int limit = menu.askInt("Limit", 10);
        int skip = menu.askInt("Skip", 0);
        List<Product> products = productService.list(limit, skip);
        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            products.forEach(p -> System.out.printf("%d | %s | %.2f%n",
                    p.getId(), p.getTitle(), p.getPrice()));
        }
    }

    private void searchProducts() {
        String q = menu.askString("Termo de busca");
        if (q.isEmpty()) {
            System.out.println("Termo de busca vazio.");
            return;
        }
        List<Product> results = productService.search(q);
        if (results.isEmpty()) {
            System.out.println("Nenhum produto encontrado para: " + q);
        } else {
            results.forEach(p -> System.out.printf("%d | %s | %.2f%n",
                    p.getId(), p.getTitle(), p.getPrice()));
        }
    }
}
