package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvitationDto {
    private long id;
    private String idInvitation;
    private String emailInvite;
    private String idSoutenance;
    private Boolean pending;
    private Boolean accepted;
}
