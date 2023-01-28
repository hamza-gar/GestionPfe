package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto addNotification(NotificationDto notificationDto);
    NotificationDto getNotification(String idNotification);
    NotificationDto getNotificationById(String id);
    NotificationDto updateNotification(String id,NotificationDto notificationDto);
    void deleteNotification(String id);
    void deleteAllNotifications();
    List<NotificationDto> getAllNotifications(int page, int limit);
}
