package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.RemarqueDto;

import java.util.List;

public interface RemarqueService {
    RemarqueDto addRemarque(String username,RemarqueDto remarqueDto);
    List<RemarqueDto> getMyRemarques(String username, int page, int limit);
    RemarqueDto getRemarqueByIdRemarque(String id);
    RemarqueDto updateRemarque(String id,RemarqueDto remarque);
    void deleteRemarque(String id);
    List<RemarqueDto> getAllRemarques( int page, int limit);
    Long countRemarque(String username);
}
