package com.TODO.TODO.API.service;

import com.TODO.TODO.API.dtos.TodoRequestDTO;
import com.TODO.TODO.API.dtos.TodoResponseDTO;
import com.TODO.TODO.API.dtos.TodoUpdateDTO;
import com.TODO.TODO.API.entity.Todo;
import com.TODO.TODO.API.exception.ResourceAlreadyExistsException;
import com.TODO.TODO.API.exception.ResourceNotFoundException;
import com.TODO.TODO.API.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoResponseDTO> findAll() {

        return todoRepository.findAll().stream()
                .map(todo -> new TodoResponseDTO(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getCreatedAt(),
                        todo.getUpdatedAt(),
                        todo.getStatus()
                ))
                .toList();
    }

    public TodoResponseDTO findById(Long id){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException(("Todo não encontrado")));

        return new TodoResponseDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getStatus()
        );
    }

    @Transactional
    public TodoResponseDTO create(TodoRequestDTO dto){

        Todo todo = new Todo();

        if (todoRepository.existsByTitle(dto.title())){
            throw new ResourceAlreadyExistsException("Titulo já existe");
        }

        todo.setTitle(dto.title());
        todo.setDescription(dto.description());
        todo.setStatus(dto.status());

        // Aqui o JPA salva e dispara @PrePersist
        todoRepository.save(todo);

        return new TodoResponseDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getStatus()
        );
    }


    // No TodoService
    public TodoResponseDTO update(Long id, TodoUpdateDTO update) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TODO não encontrado"));

        // Verifica se veio valor no DTO antes de alterar a entidade
        if (update.title() != null && !update.title().isBlank()) {
            todo.setTitle(update.title());
        }

        if (update.description() != null && !update.description().isBlank()) {
            todo.setDescription(update.description());
        }

        if (update.status() != null) {
            todo.setStatus(update.status());
        }

        todoRepository.save(todo);

        return new TodoResponseDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getStatus()
        );
    }

    public void delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TODO não encontrado"));

        todoRepository.delete(todo);
    }




}
