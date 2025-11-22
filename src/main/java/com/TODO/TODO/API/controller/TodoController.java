package com.TODO.TODO.API.controller;
import com.TODO.TODO.API.dtos.TodoRequestDTO;
import com.TODO.TODO.API.dtos.TodoResponseDTO;
import com.TODO.TODO.API.dtos.TodoUpdateDTO;
import com.TODO.TODO.API.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> listAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> create(@Valid @RequestBody TodoRequestDTO dto) {
        TodoResponseDTO created = todoService.create(dto);

        URI location = URI.create("/api/todos/" + created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateDTO dto
    ) {
        return ResponseEntity.ok(todoService.update(id, dto));
    }


    // update parcial
    @PatchMapping("{id}")
    public ResponseEntity<TodoResponseDTO> updatePartial(
            @PathVariable Long id,
            @RequestBody TodoUpdateDTO dto
    ){
        return ResponseEntity.ok(todoService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}
