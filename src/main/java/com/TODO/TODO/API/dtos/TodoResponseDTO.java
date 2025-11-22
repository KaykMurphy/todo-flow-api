package com.TODO.TODO.API.dtos;

import com.TODO.TODO.API.enums.TodoStatus;

import java.time.LocalDateTime;


public record TodoResponseDTO(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updateAt,
        TodoStatus status
) {}




