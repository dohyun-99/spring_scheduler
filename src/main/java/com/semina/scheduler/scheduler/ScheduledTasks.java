package com.semina.scheduler.scheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@NoArgsConstructor
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 1. fixedDelay
    @Scheduled(fixedDelay = 3000)
    public static void fixedDelayJob() throws InterruptedException{
        log.info("🍒Delay Job 시작: " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(5);
        log.info("🍒Delay Job 종료: " + dateFormat.format(new Date()));
    }

    // 2. fixedRate
    @Scheduled(fixedRate = 3000)
    public static void fixedRateJob() throws InterruptedException{
        log.info("🍌Rate job 시작: " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(5);
        log.info("🍌Rate job 종료: " + dateFormat.format(new Date()));
    }

    // 3. Cron 표현식 사용
    @Scheduled(cron = "*/5 * * * * *")
    public static void cronJob() {
        log.info("🥝Cron Job : " + dateFormat.format(new Date()) + "\n");
    }

    // 4. fixedRate & initialDelay
    @Scheduled(fixedRate = 1000, initialDelay=3000)
    public static void fixedRateDelay() throws InterruptedException {
        log.info("🫐Delayed Rate job 시작: " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(5);
        log.info("🫐Delayed job 종료: " + dateFormat.format(new Date()) + "\n");
    }

    static {
        log.info("🍇실제 작업 시작 시간: " + dateFormat.format(new Date()));
    }
}