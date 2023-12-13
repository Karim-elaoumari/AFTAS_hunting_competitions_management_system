package com.aftas_backend.web.rest.controllers;

import com.aftas_backend.handlers.response.ResponseMessage;
import com.aftas_backend.models.entities.Level;
import com.aftas_backend.services.LevelService;
import com.aftas_backend.web.rest.vms.level.LevelRequestVM;
import com.aftas_backend.web.rest.vms.level.LevelResponseVM;
import jakarta.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LevelService levelService;
    public LevelController(LevelService levelService){
        this.levelService = levelService;
    }
    @GetMapping
    public ResponseEntity getAllLevels(@ParameterObject Pageable pageable){
        List<Level> levels = levelService.getAllLevels(pageable);
        List<LevelResponseVM> levelResponseVMS = new ArrayList<>();
        for(Level level:levels){
            levelResponseVMS.add(LevelResponseVM.fromLevel(level));
        }
        return ResponseMessage.ok(levelResponseVMS,"Levels retrieved successfully");
    }
    @PostMapping
    public ResponseEntity createLevel(@Valid @RequestBody LevelRequestVM levelVM){
        Level createdLevel = levelService.createLevel(levelVM.toLevel());
        LevelResponseVM levelResponseVM = LevelResponseVM.fromLevel(createdLevel);
        return ResponseMessage.created(levelResponseVM,"Level created successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity getLevelById(@PathVariable Long id){
        Level level = levelService.findLevelById(id);
        LevelResponseVM levelResponseVM = LevelResponseVM.fromLevel(level);
        return ResponseMessage.ok(levelResponseVM,"Level retrieved successfully");
    }
    @GetMapping("/code/{code}")
    public ResponseEntity getLevelByCode(@PathVariable Integer code){
        Level level = levelService.getLevelByCode(code);
        LevelResponseVM levelResponseVM = LevelResponseVM.fromLevel(level);
        return ResponseMessage.ok(levelResponseVM,"Level retrieved successfully");
    }
    @PutMapping("/{code}")
    public ResponseEntity updateLevel(@Valid @PathVariable Integer code, @RequestBody LevelRequestVM levelVM){
        Level updatedLevel = levelService.updateLevel(levelVM.toLevel(), code);
        LevelResponseVM levelResponseVM = LevelResponseVM.fromLevel(updatedLevel);
        return ResponseMessage.ok(levelResponseVM,"Level updated successfully");
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteLevel(@PathVariable Long id){
//        levelService.deleteLevelById(id);
//        return ResponseMessage.ok(null,"Level deleted successfully");
//    }

}
