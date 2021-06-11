package io.quarkus.todospringquarkus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/limit/{count}")
    public List<TodoEntity> findCount(@PathVariable("count") int count) {
        return this.todoRepository.findAll(PageRequest.of(0, count)).getContent();
    }
 
    @GetMapping
    public List<TodoEntity> findAll() {
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/{id}")
    public TodoEntity findById(@PathVariable("id") Long id) {
        return todoRepository.findById(id).get();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody TodoEntity resource) {
        todoRepository.save(resource);
    }
 
    @PostMapping
    @Transactional
    public TodoEntity create(@RequestBody TodoEntity resource) {
        return todoRepository.save(resource);
    }
 
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
    }
}
