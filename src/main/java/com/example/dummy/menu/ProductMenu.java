package com.example.dummy.menu;

import java.util.Scanner;

public class ProductMenu extends MainMenu {

    public ProductMenu() {
        super();
    }

    @Override
    public void print() {
        System.out.println("\n=== Menu de Produtos ===");
        System.out.println("1. Listar produtos");
        System.out.println("2. Buscar produto por texto");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
    }


}
