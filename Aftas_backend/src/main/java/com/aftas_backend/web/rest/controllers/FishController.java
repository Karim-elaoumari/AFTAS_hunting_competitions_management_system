package com.aftas_backend.web.rest.controllers;

import com.aftas_backend.handlers.response.ResponseMessage;
import com.aftas_backend.models.entities.Fish;
import com.aftas_backend.services.FishService;
import com.aftas_backend.web.rest.vms.fish.FishRequestVM;
import com.aftas_backend.web.rest.vms.fish.FishResponseVM;
import jakarta.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@PreAuthorize(value = "hasRole('MANAGER') or hasRole('JURY')")
public class FishController {
    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public ResponseEntity getAllFishes(@ParameterObject Pageable pageable, @RequestParam(required = false, name = "search") String search) {
        List<Fish> fishes = fishService.getAllFishes(pageable, search);
        List<FishResponseVM> fishResponseVMS = new ArrayList<>();
        for (Fish fish : fishes) {
            fishResponseVMS.add(FishResponseVM.fromFish(fish));
        }
        return ResponseMessage.ok(fishResponseVMS, "Fishes retrieved successfully");
    }

    @PostMapping
    public ResponseEntity createFish(@Valid @RequestBody FishRequestVM fishVM) {
        Fish createdFish = fishService.createFish(fishVM.toFish());
        FishResponseVM fishResponseVM = FishResponseVM.fromFish(createdFish);
        return ResponseMessage.created(fishResponseVM, "Fish created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        FishResponseVM fishResponseVM = FishResponseVM.fromFish(fish);
        return ResponseMessage.ok(fishResponseVM, "Fish retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFish(@Valid @PathVariable Long id, @RequestBody FishRequestVM fishVM) {
        Fish updatedFish = fishService.updateFish(fishVM.toFish(), id);
        FishResponseVM fishResponseVM = FishResponseVM.fromFish(updatedFish);
        return ResponseMessage.ok(fishResponseVM, "Fish updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
        return ResponseMessage.ok(null, "Fish deleted successfully");
    }

    @GetMapping("name/{name}")
    public ResponseEntity getFishByName(@PathVariable String name) {
        Fish fish = fishService.getFishByName(name);
        FishResponseVM fishResponseVM = FishResponseVM.fromFish(fish);
        return ResponseMessage.ok(fishResponseVM, "Fish retrieved successfully");
    }

    @GetMapping("/count")
    public ResponseEntity countFishes() {
        Long count = fishService.countFishes();
        return ResponseMessage.ok(count, "Fishes counted successfully");
    }

}
