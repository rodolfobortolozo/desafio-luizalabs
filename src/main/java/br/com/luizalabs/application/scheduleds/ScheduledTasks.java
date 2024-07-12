package br.com.luizalabs.application.scheduleds;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public abstract  class ScheduledTasks {

    @Scheduled(fixedRateString = "${task.fixedRate}", initialDelayString = "${task.initialDelay}")
    public void executeTask() {
        task();
    }

    public abstract void task();
}
