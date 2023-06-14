package com.spring.todomanagement.service;

import com.spring.todomanagement.dto.TodoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();


    TodoDto updateTodos(TodoDto todoDto,Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);
}
