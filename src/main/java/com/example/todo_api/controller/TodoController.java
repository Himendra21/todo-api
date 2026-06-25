package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoRequest;
import com.example.todo_api.model.Todos;
import com.example.todo_api.service.TodoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // optional for frontend
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<Todos> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Todos getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Todos create(@Valid @RequestBody TodoRequest request) {
        Todos todo = new Todos();
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());

        return service.create(todo);
    }


    // UPDATE
    @PutMapping("/{id}")
    public Todos update(@PathVariable Long id, @RequestBody Todos updated) {
        return service.update(id, updated);
    }

    // DELETE (FIXED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}