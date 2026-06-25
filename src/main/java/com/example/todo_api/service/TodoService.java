package com.example.todo_api.service;

import com.example.todo_api.exception.TodoNotFoundException;
import com.example.todo_api.model.Todos;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<Todos> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public Todos getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Todo not found with id: " + id
                        )
                );
    }

    // CREATE
    public Todos create(Todos todo) {
        return repository.save(todo);
    }

    // UPDATE
    public Todos update(Long id, Todos updated) {
        Todos existing = getById(id);

        existing.setTitle(updated.getTitle());
        existing.setCompleted(updated.isCompleted());

        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }

        repository.deleteById(id);
    }
}