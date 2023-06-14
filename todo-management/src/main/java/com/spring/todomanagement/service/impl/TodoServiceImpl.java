package com.spring.todomanagement.service.impl;

import com.spring.todomanagement.dto.TodoDto;
import com.spring.todomanagement.entity.Todo;
import com.spring.todomanagement.exception.ResourceNotfoundException;
import com.spring.todomanagement.repository.TodoRepository;
import com.spring.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
//    convert TodoDto into Todo Jpa Entity

        Todo todo = modelMapper.map(todoDto, Todo.class);

//        Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

//        Convert saved Todo Jpa Entity  object into TodoDto Object


        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Todo not found with id:" + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((s) -> modelMapper.map(s, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodos(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Todo not found with id:" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Todo not found with id:" + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedtodo = todoRepository.save(todo);
        return modelMapper.map(updatedtodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Todo not found with id:" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedtodo = todoRepository.save(todo);
        return modelMapper.map(updatedtodo, TodoDto.class);
    }




}
