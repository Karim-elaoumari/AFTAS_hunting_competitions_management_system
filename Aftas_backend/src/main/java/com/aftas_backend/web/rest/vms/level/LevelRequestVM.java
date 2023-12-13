package com.aftas_backend.web.rest.vms.level;

import com.aftas_backend.models.entities.Level;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LevelRequestVM(
        @NotNull(message = "points are required")
        @DecimalMin(value = "0.0", message = "points must be greater than 0.0")
        Double points,
        @NotBlank(message = "description is required")
        String description

) {
    public Level toLevel() {
        return Level.builder()
                .points(points)
                .description(description)
                .build();
    }
}
