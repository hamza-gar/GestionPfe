package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.RendezvousDto;

import java.util.Date;
import java.util.List;

public interface RendezvousService {
    RendezvousDto addRendezvous(String username);
    RendezvousDto getRendezvousByDate(Date dateRendezvous);
    RendezvousDto getRendezvousByIdRendezvous(String username,String id);
    RendezvousDto fixRendezVous(String id,RendezvousDto rendezvous);
    RendezvousDto voteRendezvous(String username,RendezvousDto rendezvousDto);
    void deleteRendezvous(String id);
    List<RendezvousDto> getAllRendezvous(String username,int page, int limit);
}
