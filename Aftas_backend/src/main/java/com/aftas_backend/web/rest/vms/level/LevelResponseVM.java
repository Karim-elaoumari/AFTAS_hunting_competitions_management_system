package com.aftas_backend.web.rest.vms.level;

import com.aftas_backend.models.entities.Level;

public record LevelResponseVM(
        Long id,
        Integer code,
        Double points,
        String description
) {
    public static LevelResponseVM fromLevel(Level level) {
        return new LevelResponseVM(
                level.getId(),
                level.getCode(),
                level.getPoints(),
                level.getDescription()
        );
    }
}
