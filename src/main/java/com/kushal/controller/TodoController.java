package com.kushal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kushal.model.App;
import com.kushal.repository.TodoRepo;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<App> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<App> save(@RequestBody App app) {
        App savedApp = todoRepo.save(app);
        return new ResponseEntity<>(savedApp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<App> update(@PathVariable Long id, @RequestBody App app) {
        if (!todoRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        app.setId(id);
        App updatedApp = todoRepo.save(app);
        return new ResponseEntity<>(updatedApp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!todoRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
