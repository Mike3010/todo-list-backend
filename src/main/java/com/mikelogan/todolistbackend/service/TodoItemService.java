package com.mikelogan.todolistbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mikelogan.todolistbackend.dto.TodoItemDto;
import com.mikelogan.todolistbackend.model.TodoItem;
import com.mikelogan.todolistbackend.model.User;
import com.mikelogan.todolistbackend.repository.TodoItemRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {
    
    UserService userService;
    TodoItemRepository todoItemRepository;
    ModelMapper modelMapper;

    @Autowired
    public TodoItemService(final TodoItemRepository todoItemRepository, final UserService userService) {

        this.todoItemRepository = todoItemRepository;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    public List<TodoItemDto> getAllTodoItems() {
        
        User user = this.userService.getLoggedInUser();

        final List<TodoItem> todoItems = (List<TodoItem>) this.todoItemRepository.findByUserOrderBySortAsc(user);
        final List<TodoItemDto> targetList =
            todoItems
                .stream()
                .map(source -> this.modelMapper.map(source, TodoItemDto.class))
                .collect(Collectors.toList());

        return targetList;
    }

	public void add(final TodoItemDto todoItemDto) {
        
        User user = this.userService.getLoggedInUser();

        final TodoItem todoItem = this.modelMapper.map(todoItemDto, TodoItem.class);
        todoItem.setUser(user);
        this.todoItemRepository.save(todoItem);
    }

    public boolean update(final TodoItemDto todoItemDto) {

        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(todoItemDto.getId());
        User user = this.userService.getLoggedInUser();

        //does item exist?
        if(todoItem.isPresent()) {
            //does item belong to user?
            if(!todoItem.get().getUser().equals(user)) {
                return false;
            }
            final TodoItem todoItemNew = this.modelMapper.map(todoItemDto, TodoItem.class);
            todoItemNew.setUser(user);
            this.todoItemRepository.save(todoItemNew);
            return true;
        }

        return false;
	}

	public TodoItemDto get(final UUID id) {
        
        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        User user = this.userService.getLoggedInUser();

        if(todoItem.isPresent()) {
            
            if(!todoItem.get().getUser().equals(user)) {
                return null;
            }
            return this.modelMapper.map(todoItem.get(), TodoItemDto.class);
        }
        return null;
	}

	public boolean delete(final UUID id) {
        
        final Optional<TodoItem> todoItem = this.todoItemRepository.findById(id);
        User user = this.userService.getLoggedInUser();

        //does item exist?
        if(todoItem.isPresent()) {
            //does item belong to user?
            if(!todoItem.get().getUser().equals(user)) {
                return false;
            }
            this.todoItemRepository.deleteById(id);
            return true;
        }
        return false;
	}
}
