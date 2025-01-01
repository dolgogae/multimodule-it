package com.example.mmitservice.domain.schedule;

import com.example.mmitservice.domain.service.StatusManager;
import com.example.mmitservice.global.enums.DeployDayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LegacyScheduler {
    private final StatusManager statusManager;

    @Scheduled(cron = "0 */1 * * * *")
    public void processA(){
        // check release day logic
        if (statusManager.getDeployDayStatus().equals(DeployDayStatus.DEPLOY_DAY)){
            // change status
        }
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void processB(){
        if (statusManager.getDeployDayStatus().equals(DeployDayStatus.NOT_DEPLOY_DAY)){
            return;
        }

        // process logic
    }
}
