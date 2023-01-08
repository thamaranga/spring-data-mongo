package com.hasithat.springdatamongo.dto;

import com.hasithat.springdatamongo.document.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private Task task;
    private String message;
}
