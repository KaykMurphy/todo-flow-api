package com.TODO.TODO.API.repository;

import com.TODO.TODO.API.entity.Todo;
import com.TODO.TODO.API.enums.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByStatus(TodoStatus status);
    boolean  existsByTitle(String title);
    boolean existsById(Long id);

}

