package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemarqueDto {
    private long id;
    private String idRemarque;
    private String remarque;
    private Float note;
//    private ArchiveDto archive;
}