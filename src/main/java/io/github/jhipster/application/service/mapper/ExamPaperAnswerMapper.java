package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.ExamPaperAnswer;
import io.github.jhipster.application.service.dto.ExamPaperAnswer;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExamPaperAnswer} and its DTO {@link ExamPaperAnswer}.
 */
@Mapper(componentModel = "spring", uses = { ExamPaperMapper.class })
public interface ExamPaperAnswerMapper
  extends EntityMapper<ExamPaperAnswer, ExamPaperAnswer> {
  @Mapping(target = "examPaper", source = "examPaper", qualifiedByName = "id")
  ExamPaperAnswer toDto(ExamPaperAnswer s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ExamPaperAnswer toDtoId(ExamPaperAnswer examPaperAnswer);
}
