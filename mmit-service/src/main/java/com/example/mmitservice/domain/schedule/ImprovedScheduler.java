package com.example.mmitservice.domain.schedule;

import com.example.mmitservice.domain.schedule.job.SomeJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ImprovedScheduler {
    private final SomeJob someJob;

    @Scheduled(cron = "0 */1 * * * *")
    public void triggerAJob(){
        someJob.processA()
                .processB()
                .processC()
                .end();
    }
}
