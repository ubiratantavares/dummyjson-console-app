package com.example.dummy.app;

import com.example.dummy.http.HttpClient;
import com.example.dummy.controller.Controller;
import com.example.dummy.controller.MainController;
import com.example.dummy.service.ProductService;
import com.example.dummy.service.TodoService;

public class Main {

    private static final String BASE_URL = "https://dummyjson.com";

    public static void main(String[] args) {
        HttpClient client = new HttpClient(BASE_URL);
        ProductService productService = new ProductService(client);
        TodoService todoService = new TodoService(client);
        Controller mainController = new MainController(productService, todoService);
        mainController.execute();
    }
}
