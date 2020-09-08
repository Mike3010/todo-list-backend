package com.mikelogan.todolistbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mikelogan.todolistbackend.dto.TodoItemDto;
import com.mikelogan.todolistbackend.model.TodoItem;
import com.mikelogan.todolistbackend.repository.TodoItemRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {
    
    TodoItemRepository todoItemRepository;
    ModelMapper modelMapper;

    @Autowired
    public TodoItemService(final TodoItemRepository todoItemRepository) {

        this.todoItemRepository = todoItemRepository;
        this.modelMapper = new ModelMapper();
    }

    private String getUserEmailAdress() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return jwt.getClaim("email");
    }

    public List<TodoItemDto> getAllTodoItems() {
        
        final List<TodoItem> todoItems = (List<TodoItem>) this.todoItemRepository.findByEmail(this.getUserEmailAdress());
        
        final List<TodoItemDto> targetList =
            todoItems
                .stream()
                .map(source -> this.modelMapper.map(source, TodoItemDto.class))
                .collect(Collectors.toList());

        return targetList;
    }

	public void add(final TodoItemDto todoItemDto) {
        
        final TodoItem todoItem = this.modelMapper.map(todoItemDto, TodoItem.class);
        todoItem.setEmail(this.getUserEmailAdress());
        this.todoItemRepository.save(todoItem);
    }

    public boolean update(final TodoItemDto todoItemDto) {

        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(todoItemDto.getId());
        //does item exist?
        if(todoItem.isPresent()) {
            //does item belong to user?
            if(!todoItem.get().getEmail().equals(this.getUserEmailAdress())) {
                return false;
            }
            final TodoItem todoItemNew = this.modelMapper.map(todoItemDto, TodoItem.class);
            todoItemNew.setEmail(this.getUserEmailAdress());
            this.todoItemRepository.save(todoItemNew);
            return true;
        }

        return false;
	}

	public TodoItemDto get(final UUID id) {
        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        if(todoItem.isPresent()) {
            
            if(!todoItem.get().getEmail().equals(this.getUserEmailAdress())) {
                return null;
            }
            return this.modelMapper.map(todoItem.get(), TodoItemDto.class);
        }
        return null;
	}

	public boolean delete(final UUID id) {
        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        //does item exist?
        if(todoItem.isPresent()) {
            //does item belong to user?
            if(!todoItem.get().getEmail().equals(this.getUserEmailAdress())) {
                return false;
            }
            this.todoItemRepository.deleteById(id);
            return true;
        }
        return false;
	}
}
