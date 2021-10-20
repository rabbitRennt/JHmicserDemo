package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.TaskExam;
import io.github.jhipster.application.service.dto.TaskExam;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskExam} and its DTO {@link TaskExam}.
 */
@Mapper(
  componentModel = "spring",
  uses = { ExamPaperMapper.class, TextContentMapper.class }
)
public interface TaskExamMapper extends EntityMapper<TaskExam, TaskExam> {
  @Mapping(target = "examPaper", source = "examPaper", qualifiedByName = "id")
  @Mapping(
    target = "textContent",
    source = "textContent",
    qualifiedByName = "id"
  )
  TaskExam toDto(TaskExam s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  TaskExam toDtoId(TaskExam taskExam);
}
