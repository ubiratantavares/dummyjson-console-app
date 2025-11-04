package com.example.dummy.menu;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class MainMenu implements Menu {

    private final Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void print() {
        System.out.println("\n=== Catálogo & Tarefas (DummyJSON) ===");
        System.out.println("1. Menu de Produtos");
        System.out.println("2. Menu de Tarefas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int askInt(String label, int defaultValue) {
        System.out.printf("%s (default=%d): ", label, defaultValue);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Usando valor padrão: " + defaultValue);
            return defaultValue;
        }
    }

    public String askString(String label) {
        System.out.println(label + ": ");
        return scanner.nextLine().trim();
    }

    public boolean askBoolean(String label) {
        System.out.print(label + ": ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("true") || input.equals("t") || input.equals("1");
    }
}
