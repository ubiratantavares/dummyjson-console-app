package com.example.dummy.model;

import lombok.Data;

import java.util.List;

/**
 * Wrapper response for /todos endpoints
 */
@Data
public class TodoListResponse {
    private List<Todo> todos;
    private int total;
    private int skip;
    private int limit;
}
