package com.mikelogan.todolistbackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mikelogan.todolistbackend.model.TodoItem;

import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, UUID> {

  List<TodoItem> findByTitle(String title);

  Optional<TodoItem> findById(UUID id);
}