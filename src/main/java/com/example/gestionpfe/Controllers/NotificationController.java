package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Dto.NotificationDto;
import com.example.gestionpfe.Requests.NotificationRequest;
import com.example.gestionpfe.Responses.NotificationResponse;
import com.example.gestionpfe.Services.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    NotificationService notificationService;

    // @PreAuthorize("hasAuthority('GET_NOTIFCATION_AUTHORITY')")
    @GetMapping(path="/{id}")
    public ResponseEntity<NotificationResponse>getNotification(@PathVariable String id){
        NotificationDto notificationDto = notificationService.getNotificationById(id);
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse = modelMapper.map(notificationDto,NotificationResponse.class);
        return ResponseEntity.ok(notificationResponse);
    }
    //@PreAuthorize("hasAuthority('GET_ALL_NOTIFICATION_AUTHORITY')")
    @GetMapping
    public List<NotificationResponse> getAllNotifications(@RequestParam(value = "page") int page, @RequestParam(value="limit" )int limit){
        List<NotificationResponse> notificationResponse = new ArrayList<>();
        List<NotificationDto> notifications = notificationService.getAllNotifications(page,limit);

        for(NotificationDto notificationDto:notifications){
            NotificationResponse notification = new NotificationResponse();
            notification = modelMapper.map(notificationDto,NotificationResponse.class);

            notificationResponse.add(notification);
        }
        return notificationResponse;
    }

    //@PreAuthorize("hasAuthority('ADD_NOTIFICATION_AUTHORITY')")
    @PostMapping
    public ResponseEntity<NotificationResponse> addNotification(@RequestBody NotificationRequest notificationRequest){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto = modelMapper.map(notificationRequest,NotificationDto.class);

        NotificationDto Addnotification = notificationService.addNotification(notificationDto);
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse = modelMapper.map(Addnotification,NotificationResponse.class);
        return ResponseEntity.ok(notificationResponse);
    }

    //@PreAuthorize("hasAuthority('UPDATE_NOTIFICATION_AUTHORITY')")
    @PutMapping(path="/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(@PathVariable String id,@RequestBody NotificationRequest notificationRequest){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto = modelMapper.map(notificationRequest,NotificationDto.class);

        NotificationDto UpdateNotification = notificationService.updateNotification(id,notificationDto);
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse = modelMapper.map(UpdateNotification,NotificationResponse.class);
        return new ResponseEntity<NotificationResponse>(notificationResponse, HttpStatus.ACCEPTED);
    }
    //@PreAuthorize("hasAuthority('DELETE_NOTIFICATION_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable String id){
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('DELETE_ALL_NOTIFICATION_BY_USER_AUTHORITY')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAllNotificationByUser(){
        notificationService.deleteAllNotifications();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
