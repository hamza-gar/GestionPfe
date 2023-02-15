package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.DomaineDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DomaineService {
    DomaineDto addDomaine(DomaineDto domainDto);
    DomaineDto getDomaine(String nomDomaine);
    DomaineDto getDomaineById(String id);
    DomaineDto updateDomaine(long id,DomaineDto domainDto);
    void deleteDomaine(long id);
    List<DomaineDto> getAllDomaines(int page, int limit);

}
