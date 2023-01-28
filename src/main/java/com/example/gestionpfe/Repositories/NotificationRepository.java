package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Notification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {
Notification findByIdNotification(String idNotification);
Notification findById(String id);
}
