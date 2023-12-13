package com.aftas_backend.web.rest.vms.fish;

import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.models.entities.Level;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FishRequestVM(
        @NotBlank(message = "Name is required")
         String name,
         @NotBlank(message = "Info are required")
         String info,
         @NotNull(message = "AverageWeight is required")
         @DecimalMin(value = "0.0", message = "AverageWeight most")
         Double average_weight,
         @NotNull(message = "Level Code is is required")
         @Min(value = 0,message = "Level Code most be greater than 0")
         Integer level_code
) {
    public Fish toFish(){
        return Fish.builder()
                .name(name)
                .info(info)
                .averageWeight(average_weight)
                .level(Level.builder()
                        .code(level_code)
                        .build())
                .build();
    }
}
