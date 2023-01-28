package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.NotificationDto;
import com.example.gestionpfe.Entities.Notification;
import com.example.gestionpfe.Repositories.NotificationRepository;
import com.example.gestionpfe.Services.NotificationService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(NotificationServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private Utils util;


    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) {
        Notification checkNotification = notificationRepository.findByIdNotification(notificationDto.getIdNotification());

        if(checkNotification != null) throw new RuntimeException("Notification already exist !!!");
        Notification notificationEntity = new Notification();
        notificationEntity = modelMapper.map(notificationDto, Notification.class);

        Notification newNotification = notificationRepository.save(notificationEntity);

        NotificationDto newNotificationDto = new NotificationDto();
        newNotificationDto = modelMapper.map(newNotification, NotificationDto.class);
        logger.info("Notification added successfully");

        return newNotificationDto;
    }

    @Override
    public NotificationDto getNotification(String idNotification) {
        Notification notificationEntity =  notificationRepository.findByIdNotification(idNotification);
        if(notificationEntity==null)throw new RuntimeException(idNotification);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto = modelMapper.map(notificationEntity, NotificationDto.class);
        logger.info("Notification found successfully");

        return notificationDto;
    }

    @Override
    public NotificationDto getNotificationById(String id) {
        Notification notificationEntity =  notificationRepository.findById(id);
        if(notificationEntity==null)throw new RuntimeException(id);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto = modelMapper.map(notificationEntity, NotificationDto.class);
        logger.info("Notification found successfully");

        return notificationDto;
    }

    @Override
    public NotificationDto updateNotification(String id, NotificationDto notificationDto) {
        Notification notificationEntity = notificationRepository.findById(id);
        if(notificationEntity==null)throw new RuntimeException(id);

        notificationEntity.setMessage(notificationDto.getMessage());

        Notification updatedNotification = notificationRepository.save(notificationEntity);

        NotificationDto updatedNotificationDto = new NotificationDto();
        updatedNotificationDto = modelMapper.map(updatedNotification, NotificationDto.class);
        logger.info("Notification updated successfully");

        return updatedNotificationDto;
    }

    @Override
    public void deleteNotification(String id) {
        Notification notificationEntity = notificationRepository.findById(id);

        if(notificationEntity==null)throw new RuntimeException(id);

        notificationRepository.delete(notificationEntity);

    }

    @Override
    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }

    @Override
    public List<NotificationDto> getAllNotifications(int page, int limit) {
        List<NotificationDto>notificationsDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Notification> notificationsPage = notificationRepository.findAll(pageableRequest);
        List<Notification> notifications = notificationsPage.getContent();

        for(Notification notificationEntity:notifications){
            NotificationDto notificationDto = new NotificationDto();
            notificationDto = modelMapper.map(notificationEntity, NotificationDto.class);
            notificationsDto.add(notificationDto);
        }
        logger.info("All notifications found successfully");

        return notificationsDto;
    }
}
