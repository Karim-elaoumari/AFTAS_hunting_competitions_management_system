package com.aftas_backend.web.rest.controllers;

import com.aftas_backend.handlers.response.ResponseMessage;
import com.aftas_backend.models.entities.Hunting;
import com.aftas_backend.services.HuntingService;
import com.aftas_backend.web.rest.vms.hunting.HuntingRequestVM;
import com.aftas_backend.web.rest.vms.hunting.HuntingResponseVM;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/huntings")
@PreAuthorize(value = "hasRole('MANAGER') or hasRole('JURY')")
public class HuntingController {
    private final HuntingService huntingService;
    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }
    @GetMapping("member/{memberNumber}/competition/{competitionCode}")
    public ResponseEntity getHuntingsByMemberNumberAndCompetitionCode(@ParameterObject Pageable pageable, @PathParam("memberNumber") Integer memberNumber, @PathParam("competitionCode") String competitionCode) {
        List<Hunting> huntings = huntingService.getHuntingsByMemberNumberAndCompetitionCode(memberNumber, competitionCode, pageable);
        List<HuntingResponseVM> huntingResponseVMS = new ArrayList<>();
        for (Hunting hunting : huntings) {
            huntingResponseVMS.add(HuntingResponseVM.fromHunting(hunting));
        }
        return ResponseMessage.ok(huntingResponseVMS, "Huntings retrieved successfully");
    }
    @GetMapping("competition/{competitionCode}")
    public ResponseEntity getHuntingsByCompetitionCode(@ParameterObject Pageable pageable, @PathParam("competitionCode") String competitionCode) {
        List<Hunting> huntings = huntingService.getHuntingsByCompetitionCode(competitionCode, pageable);
        List<HuntingResponseVM> huntingResponseVMS = new ArrayList<>();
        for (Hunting hunting : huntings) {
            huntingResponseVMS.add(HuntingResponseVM.fromHunting(hunting));
        }
        return ResponseMessage.ok(huntingResponseVMS, "Huntings retrieved successfully");
    }
    @GetMapping("member/{memberNumber}")
    public ResponseEntity getHuntingsByMemberNumber(@ParameterObject Pageable pageable, @PathParam("memberNumber") Integer memberNumber) {
        List<Hunting> huntings = huntingService.getHuntingsByMemberNumber(memberNumber, pageable);
        List<HuntingResponseVM> huntingResponseVMS = new ArrayList<>();
        for (Hunting hunting : huntings) {
            huntingResponseVMS.add(HuntingResponseVM.fromHunting(hunting));
        }
        return ResponseMessage.ok(huntingResponseVMS, "Huntings retrieved successfully");
    }
    @PostMapping
    public ResponseEntity createHunting(@Valid @RequestBody HuntingRequestVM huntingRequestVM) {
        Hunting hunting = huntingService.createHunting(huntingRequestVM.toHuntingDTO());
        HuntingResponseVM huntingResponseVM = HuntingResponseVM.fromHunting(hunting);
        return ResponseMessage.created(huntingResponseVM, "Hunting created successfully");
    }


}
