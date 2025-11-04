package com.example.dummy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Todo minimal fields: id, todo, completed, userId
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {
    private Integer id;
    private String todo;
    private Boolean completed;

    // DummyJSON uses "userId"
    @JsonProperty("userId")
    private Integer userId;

}
