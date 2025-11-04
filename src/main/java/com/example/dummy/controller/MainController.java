package com.example.dummy.controller;

import com.example.dummy.menu.MainMenu;
import com.example.dummy.menu.Menu;
import com.example.dummy.service.ProductService;
import com.example.dummy.service.TodoService;

public class MainController implements Controller {

    private final ProductController productController;
    private final TodoController todoController;
    private final Menu menu;

    public MainController(ProductService productService, TodoService todoService) {
        this.menu = new MainMenu();
        this.productController = new ProductController(productService);
        this.todoController = new TodoController(todoService);
    }

    @Override
    public void execute() {
        while (true) {
            menu.print();
            String opt = menu.getScanner().nextLine().trim();
            switch (opt) {
                case "1" -> productController.execute();
                case "2" -> todoController.execute();
                case "3" -> {return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
