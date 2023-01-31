package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvitationRequest {
    private String idInvitation;
    private Boolean accepted;
    private Boolean pending;
    private String idSoutenance;
    private String emailInvite;
}
