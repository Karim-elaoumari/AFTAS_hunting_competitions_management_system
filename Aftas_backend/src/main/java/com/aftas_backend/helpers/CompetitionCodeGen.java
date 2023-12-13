package com.aftas_backend.helpers;

import java.time.LocalDate;

public interface CompetitionCodeGen {
    String generateCode(String location, LocalDate date);
}
