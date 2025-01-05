package com.example.mmitcommon.dto;

import com.example.mmitcommon.enums.TaskType;
import lombok.Builder;

@Builder
public record TaskDto(TaskType taskType, Object payload) {
}
