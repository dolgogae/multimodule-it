package com.example.mmitcommon.dto;

import com.example.mmitcommon.enums.TaskType;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
public record TaskDto(TaskType taskType, Object payload) implements Serializable {}

