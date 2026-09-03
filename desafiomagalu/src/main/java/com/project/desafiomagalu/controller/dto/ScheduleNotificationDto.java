package com.project.desafiomagalu.controller.dto;

import com.project.desafiomagalu.entity.Channel;
import com.project.desafiomagalu.entity.Notification;
import com.project.desafiomagalu.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }

}
