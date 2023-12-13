package com.aftas_backend.web.rest.vms.fish;

import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.web.rest.vms.level.LevelResponseVM;

public record FishResponseVM(
        Long id,
        String name,
        String info,
        Double average_weight,
        LevelResponseVM level
) {
    public static FishResponseVM fromFish(Fish fish){
        return new FishResponseVM(
                fish.getId(),
                fish.getName(),
                fish.getInfo(),
                fish.getAverageWeight(),
                LevelResponseVM.fromLevel(fish.getLevel())
                );
    }
}
