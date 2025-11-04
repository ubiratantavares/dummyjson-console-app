package com.example.dummy.service;

import com.example.dummy.http.HttpClient;
import com.example.dummy.model.Todo;
import com.example.dummy.model.TodoListResponse;

import java.io.IOException;
import java.util.*;

public class TodoService {
    private final HttpClient client;

    public TodoService(HttpClient client) {
        this.client = client;
    }

    public List<Todo> list(int limit, int skip) {
        try {
            String path = String.format("/todos?limit=%d&skip=%d", limit, skip);
            Optional<TodoListResponse> opt = client.get(path, TodoListResponse.class);
            return opt.map(TodoListResponse::getTodos).orElse(Collections.emptyList());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao listar todos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Todo> add(String text, int userId) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("todo", text);
            payload.put("userId", userId);
            // DummyJSON expects POST /todos/add
            return client.post("/todos/add", payload, Todo.class);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao adicionar todo: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Todo> toggle(int id, boolean completed) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("completed", completed);
            String path = String.format("/todos/%d", id);
            return client.put(path, payload, Todo.class);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao atualizar todo: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean delete(int id) {
        try {
            String path = String.format("/todos/%d", id);
            return client.delete(path);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao deletar todo: " + e.getMessage());
            return false;
        }
    }
}
