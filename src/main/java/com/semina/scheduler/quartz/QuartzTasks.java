package com.semina.scheduler.quartz;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Service("quartz") // Quartz 사용시 명시!
@Slf4j
@NoArgsConstructor
public class QuartzTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 실행할 Job 클래스 생성
    public static class TestJob implements Job {
        public void execute(JobExecutionContext context) throws JobExecutionException {
            log.info("🍫Scheduling with Quartz : " + dateFormat.format(new Date()));
        }
    }

    public static void main(String[] args) throws SchedulerException {
        // Job 생성
        JobDetail job = JobBuilder.newJob(TestJob.class)
                .build();

        // Trigger 생성
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .withRepeatCount(5))
                .build();

        // 스케줄러 생성 및 Job, Trigger 등록
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
