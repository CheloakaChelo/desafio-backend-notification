package com.project.desafiomagalu.scheduler;

import com.project.desafiomagalu.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class TaskScheduler {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final NotificationService notificationService;

    public TaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void runTasks() {
        var dateTime = LocalDateTime.now();
        log.info("Running at {}", dateTime);
        notificationService.checkAndSend(dateTime);
    }

}
