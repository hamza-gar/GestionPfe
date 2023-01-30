package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.RemarqueDto;

import java.util.List;

public interface RemarqueService {
    RemarqueDto addRemarque(RemarqueDto remarqueDto);
    RemarqueDto getRemarqueByIdRemarque(String id);
    RemarqueDto updateRemarque(String id,RemarqueDto remarque);
    void deleteRemarque(String id);
    List<RemarqueDto> getAllRemarques( int page, int limit);
}
