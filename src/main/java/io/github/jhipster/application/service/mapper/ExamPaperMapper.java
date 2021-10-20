package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.ExamPaper;
import io.github.jhipster.application.service.dto.ExamPaper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExamPaper} and its DTO {@link ExamPaper}.
 */
@Mapper(componentModel = "spring", uses = { SubjectMapper.class })
public interface ExamPaperMapper extends EntityMapper<ExamPaper, ExamPaper> {
  @Mapping(target = "subject", source = "subject", qualifiedByName = "id")
  ExamPaper toDto(ExamPaper s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ExamPaper toDtoId(ExamPaper examPaper);
}
