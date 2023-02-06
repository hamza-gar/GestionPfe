package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.PasswordHelperDto;
import com.example.gestionpfe.Entities.PasswordHelper;

public interface PasswordHelperService {
    PasswordHelperDto createPasswordHelper(PasswordHelperDto passwordHelperDto);

    Boolean updateUserPassword(PasswordHelperDto passwordHelperDto);

    Boolean checkKey(PasswordHelperDto passwordHelperDto);

    String generateToken(String email);

    Boolean checkExpiration(String jwtToken);
}
