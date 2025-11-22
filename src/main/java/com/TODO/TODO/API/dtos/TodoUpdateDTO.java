package com.TODO.TODO.API.dtos;

import com.TODO.TODO.API.enums.TodoStatus;
import jakarta.validation.constraints.Size;

public record TodoUpdateDTO(

        @Size(min = 3, max = 100)
        String title,

        @Size(min = 3, max = 500)
        String description,
        TodoStatus status

) {
}


