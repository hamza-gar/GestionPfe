package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Invitation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends PagingAndSortingRepository<Invitation,Long> {
    Invitation findByEmailInvite(String emailInvite);
    Invitation findByIdInvitation(String idInvitation);
    Invitation findByIdSoutenance(String idSoutenance);
}
