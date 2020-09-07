package com.mikelogan.todolistbackend.controller;

import java.util.List;

import com.mikelogan.todolistbackend.dto.TodoItemDto;
import com.mikelogan.todolistbackend.service.TodoItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("todo")
public class TodoItemController {
    
    TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/")
    public List<TodoItemDto> getAll() {
        return this.todoItemService.getAllTodoItems();
    }

    @PostMapping("/")
    public HttpStatus addItem(@RequestBody TodoItemDto todoItemDto) {
        
        this.todoItemService.add(todoItemDto);
        return HttpStatus.CREATED;
    }
    
    @PutMapping("/")
    public HttpStatus updateItem(@RequestBody TodoItemDto todoItemDto) {
        
        if(this.todoItemService.update(todoItemDto)) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
