package com.project.desafiomagalu.service;

import com.project.desafiomagalu.controller.dto.ScheduleNotificationDto;
import com.project.desafiomagalu.entity.Notification;
import com.project.desafiomagalu.entity.Status;
import com.project.desafiomagalu.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository repository, NotificationRepository notificationRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto){
        repository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId){
        return repository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId){
        Optional<Notification> notification = findById(notificationId);

        if(notification.isPresent()){
            notification.get().setStatus(Status.Values.CANCELLED.toStatus());
            repository.save(notification.get());
        }
    }

    public void checkAndSend (LocalDateTime dateTime){
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()
        ), dateTime);

        notifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return n -> {
            n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);
        };
    }
}
