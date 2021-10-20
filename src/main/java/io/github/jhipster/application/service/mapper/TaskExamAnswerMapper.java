package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.TaskExamAnswer;
import io.github.jhipster.application.service.dto.TaskExamAnswer;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskExamAnswer} and its DTO {@link TaskExamAnswer}.
 */
@Mapper(componentModel = "spring", uses = { TaskExamMapper.class })
public interface TaskExamAnswerMapper
  extends EntityMapper<TaskExamAnswer, TaskExamAnswer> {
  @Mapping(target = "taskExam", source = "taskExam", qualifiedByName = "id")
  TaskExamAnswer toDto(TaskExamAnswer s);
}
