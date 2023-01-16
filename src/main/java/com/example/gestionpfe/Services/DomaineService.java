package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.DomaineDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DomaineService extends UserDetailsService {
    DomaineDto addDomaine(DomaineDto domainDto);
    DomaineDto getDomaine(String nomDomaine);
    DomaineDto getDomaineById(String id);
    DomaineDto updateDomaine(String id,DomaineDto domainDto);
    void deleteDomaine(String id);
    List<DomaineDto> getAllDomaines(int page, int limit);

}
