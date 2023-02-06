package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PasswordHelperRequest {
    private String key;
    private String password;
    private String email;
}
