package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private String idNotification;
    private String message;
    private int typeNotification;
    private Boolean vu;

}
