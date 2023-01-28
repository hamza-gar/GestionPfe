package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.RendezvousDto;

import java.util.Date;
import java.util.List;

public interface RendezvousService {
    RendezvousDto addRendezvous(RendezvousDto rendezvousDto);
    RendezvousDto getRendezvousByDate(Date dateRendezvous);
    RendezvousDto getRendezvousByIdRendezvous(String username,String id);
    RendezvousDto updateRendezvous(String id,RendezvousDto rendezvous);
    void deleteRendezvous(String id);
    List<RendezvousDto> getAllRendezvous(String username,int page, int limit);
}
