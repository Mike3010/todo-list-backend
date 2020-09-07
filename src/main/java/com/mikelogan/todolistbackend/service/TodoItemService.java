package com.mikelogan.todolistbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mikelogan.todolistbackend.dto.TodoItemDto;

import org.springframework.stereotype.Service;

@Service
public class TodoItemService {
    
    private final List<TodoItemDto> dummyItems = new ArrayList<TodoItemDto>() {

        private static final long serialVersionUID = 1L;

        {
            add(new TodoItemDto(UUID.randomUUID(), "Item 1", "Text 1", 1));             
            add(new TodoItemDto(UUID.randomUUID(), "Item 2", "Text 2", 2));             
            add(new TodoItemDto(UUID.randomUUID(), "Item 3", "Text 3", 3));                     
        } 
    };

    public List<TodoItemDto> getAllTodoItems() {
        
        return this.dummyItems;
    }

	public void add(final TodoItemDto todoItemDto) {
        todoItemDto.setId(UUID.randomUUID());
        this.dummyItems.add(todoItemDto);
    }

    public boolean update(final TodoItemDto todoItemDto) {
		for (int counter = 0; counter < this.dummyItems.size(); counter++) { 
            if(this.dummyItems.get(counter).getId().equals(todoItemDto.getId())) {
                this.dummyItems.set(counter, todoItemDto);
                return true;
            }
        }
        return false;
	}

	public TodoItemDto get(UUID id) {
        for (int counter = 0; counter < this.dummyItems.size(); counter++) { 
            if(this.dummyItems.get(counter).getId().equals(id)) {
                return this.dummyItems.get(counter);
            }
        }
        return null;
	}

	public boolean delete(UUID id) {
		for (int counter = 0; counter < this.dummyItems.size(); counter++) { 
            if(this.dummyItems.get(counter).getId().equals(id)) {
                this.dummyItems.remove(this.dummyItems.get(counter));
                return true;
            }
        }
        return false;
	}
}
