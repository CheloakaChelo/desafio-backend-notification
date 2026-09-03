package com.project.desafiomagalu.repository;

import com.project.desafiomagalu.entity.Notification;
import com.project.desafiomagalu.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);

}
