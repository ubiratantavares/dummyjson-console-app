package com.example.dummy.menu;

public class TodoMenu extends MainMenu {

    public TodoMenu() {
        super();
    }

    @Override
    public void print() {
        System.out.println("\n=== Menu de Tarefas ===");
        System.out.println("1. Listar tarefas");
        System.out.println("2. Adicionar tarefa");
        System.out.println("3. Marcar/Desmarcar tarefa");
        System.out.println("4. Remover tarefa");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");
    }
}
