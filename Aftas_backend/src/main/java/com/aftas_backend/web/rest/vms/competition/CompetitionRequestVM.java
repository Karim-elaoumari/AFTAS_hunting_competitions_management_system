package com.aftas_backend.web.rest.vms.competition;

import com.aftas_backend.models.entities.Competition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestVM(
        @NotBlank(message = "Location  is required")
        String location,
        @NotNull(message = "Date is required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent(message = "Date must be in the present or future")
        LocalDate date,
        @NotNull(message = "Start time is required")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime start_time,
        @NotNull(message = "End time is required")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime end_time,
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0.0")
        Double amount,
        @NotNull(message = "Max participants is required")
        @DecimalMin(value = "0", inclusive = false, message = "Value must be greater than 0")
        Integer max_participants,
        @NotBlank(message = "Description is required")
        String description
) {
    public Competition toCompetition() {
        return Competition.builder()
                .location(location)
                .date(date)
                .startTime(start_time)
                .endTime(end_time)
                .amount(amount)
                .maxParticipants(max_participants)
                .description(description)
                .build();
    }
}
