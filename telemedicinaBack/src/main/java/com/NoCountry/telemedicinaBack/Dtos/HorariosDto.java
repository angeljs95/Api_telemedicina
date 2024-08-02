package com.NoCountry.telemedicinaBack.Dtos;

import com.NoCountry.telemedicinaBack.Entity.Medico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorariosDto {

    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private boolean disponible;
    @JsonIgnore
    private Medico medico;
}
