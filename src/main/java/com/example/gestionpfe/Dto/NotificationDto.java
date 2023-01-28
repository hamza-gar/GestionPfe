package com.example.gestionpfe.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private long id;
    private String idNotification;
    private String message;
    private int typeNotification;
    private Boolean vu;
}
