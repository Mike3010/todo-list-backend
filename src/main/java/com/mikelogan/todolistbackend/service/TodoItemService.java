package com.mikelogan.todolistbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mikelogan.todolistbackend.dto.TodoItemDto;
import com.mikelogan.todolistbackend.model.TodoItem;
import com.mikelogan.todolistbackend.repository.TodoItemRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {
    
    TodoItemRepository todoItemRepository;
    ModelMapper modelMapper;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {

        this.todoItemRepository = todoItemRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<TodoItemDto> getAllTodoItems() {
        
        List<TodoItem> todoItems = (List<TodoItem>) this.todoItemRepository.findAll();
        
        List<TodoItemDto> targetList =
            todoItems
                .stream()
                .map(source -> this.modelMapper.map(source, TodoItemDto.class))
                .collect(Collectors.toList());

        return targetList;
    }

	public void add(final TodoItemDto todoItemDto) {
        
        TodoItem todoItem = this.modelMapper.map(todoItemDto, TodoItem.class);
        this.todoItemRepository.save(todoItem);
    }

    public boolean update(final TodoItemDto todoItemDto) {

        TodoItem todoItem = this.modelMapper.map(todoItemDto, TodoItem.class);
        this.todoItemRepository.save(todoItem);
        return true;
	}

	public TodoItemDto get(UUID id) {
        Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        if(todoItem.isPresent()) {
            
            return this.modelMapper.map(todoItem.get(), TodoItemDto.class);
        }
        return null;
	}

	public boolean delete(UUID id) {
		this.todoItemRepository.deleteById(id);
        return true;
	}
}
