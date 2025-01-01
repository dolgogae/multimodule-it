package com.example.mmitservice.domain.schedule.job;


import com.example.mmitservice.domain.service.StatusManager;
import com.example.mmitservice.global.enums.DeployDayStatus;
import com.example.mmitservice.global.enums.DeployStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SomeJob {
    private final StatusManager statusManager;

    public SomeJob processA(){
        statusManager.setDeployDayStatus(DeployDayStatus.DEPLOY_DAY);
        System.out.println("process something(A)");
        return this;
    }

    public SomeJob processB(){
        if(statusManager.getDeployDayStatus().equals(DeployDayStatus.DEPLOY_DAY)){
            System.out.println("process something(B)");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime dummyTime = LocalDateTime.now();
            if (now.isAfter(dummyTime)){
                statusManager.setDeployStatus(DeployStatus.IN_PROGRESS);
            } else {
                statusManager.setDeployStatus(DeployStatus.NOT_IN_PROGRESS);
            }
        }
        return this;
    }

    public SomeJob processC(){
        statusManager.getDeployStatus().execute();
        return this;
    }

    public void end(){
        log.info("release day: {}, deploy status: {}", statusManager.getDeployDayStatus(), statusManager.getDeployStatus());
    }
}