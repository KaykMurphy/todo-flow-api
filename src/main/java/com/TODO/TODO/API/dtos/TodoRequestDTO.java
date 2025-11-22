package com.TODO.TODO.API.dtos;

import com.TODO.TODO.API.enums.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record TodoRequestDTO (


        @NotBlank
        @Size(min = 3, max = 100)
        String title,

        @NotBlank
        @Size(min = 3, max = 100)
        String description,

        @NotNull
        TodoStatus status
) {}
