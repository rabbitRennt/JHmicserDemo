package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.ExamPaperAnswerDef;
import io.github.jhipster.application.service.dto.ExamPaperAnswerDef;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExamPaperAnswerDef} and its DTO {@link ExamPaperAnswerDef}.
 */
@Mapper(
  componentModel = "spring",
  uses = { QuestionMapper.class, ExamPaperAnswerMapper.class }
)
public interface ExamPaperAnswerDefMapper
  extends EntityMapper<ExamPaperAnswerDef, ExamPaperAnswerDef> {
  @Mapping(target = "question", source = "question", qualifiedByName = "id")
  @Mapping(
    target = "examPaperAnswer",
    source = "examPaperAnswer",
    qualifiedByName = "id"
  )
  ExamPaperAnswerDef toDto(ExamPaperAnswerDef s);
}
