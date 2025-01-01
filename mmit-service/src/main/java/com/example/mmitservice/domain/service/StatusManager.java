package com.example.mmitservice.domain.service;

import com.example.mmitservice.global.enums.DeployDayStatus;
import com.example.mmitservice.global.enums.DeployStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Accessors(chain = true)
public class StatusManager {
    private DeployStatus deployStatus = DeployStatus.NOT_IN_PROGRESS;
    private DeployDayStatus deployDayStatus = DeployDayStatus.NOT_DEPLOY_DAY;
}
