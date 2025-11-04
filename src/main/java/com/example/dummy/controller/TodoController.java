package com.example.dummy.controller;

import com.example.dummy.menu.Menu;
import com.example.dummy.menu.TodoMenu;
import com.example.dummy.model.Todo;
import com.example.dummy.service.TodoService;

import java.util.List;
import java.util.Optional;

public class TodoController implements Controller {

    private final TodoService todoService;
    private final Menu menu;

    public TodoController(TodoService todoService) {
        this.menu = new TodoMenu();
        this.todoService = todoService;
    }

    @Override
    public void execute() {
        while (true) {
            menu.print();
            String opt = menu.getScanner().nextLine().trim();
            switch (opt) {
                case "1" -> listTodos();
                case "2" -> addTodo();
                case "3" -> toggleTodo();
                case "4" -> deleteTodo();
                case "5" -> { return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listTodos() {
        int limit = menu.askInt("Limit", 10);
        int skip = menu.askInt("Skip", 0);
        List<Todo> todos = todoService.list(limit, skip);
        if (todos.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            todos.forEach(t -> System.out.printf("%d | %s | (%s) | user=%d%n",
                    t.getId(), t.getTodo(), t.getCompleted() ? "OK" : "PEND", t.getUserId()));
        }
    }

    private void addTodo() {
        String text = menu.askString("Texto da tarefa");
        int userId = menu.askInt("User ID", 1);
        if (text.isEmpty() || userId < 0) {
            System.out.println("Entrada inválida.");
            return;
        }
        Optional<Todo> created = todoService.add(text, userId);
        created.ifPresentOrElse(
                t -> System.out.printf("Criado: %d | %s | (%s) | user=%d%n",
                        t.getId(), t.getTodo(), t.getCompleted() ? "OK" : "PEND", t.getUserId()),
                () -> System.out.println("Falha ao criar tarefa.")
        );
    }

    private void toggleTodo() {
        int id = menu.askInt("ID da tarefa", 1);
        boolean completed = menu.askBoolean("Marcar como concluída (true/false)");
        Optional<Todo> updated = todoService.toggle(id, completed);
        updated.ifPresentOrElse(
                t -> System.out.printf("Atualizado: %d | %s | (%s) | user=%d%n",
                        t.getId(), t.getTodo(), t.getCompleted() ? "OK" : "PEND", t.getUserId()),
                () -> System.out.println("Falha ao atualizar tarefa.")
        );
    }

    private void deleteTodo() {
        int id = menu.askInt("ID da tarefa para remover", 1);
        boolean removed = todoService.delete(id);
        if (removed) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Falha ao remover tarefa.");
        }
    }
}
