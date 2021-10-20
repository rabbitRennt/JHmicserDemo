package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.Question;
import io.github.jhipster.application.service.dto.Question;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link Question}.
 */
@Mapper(componentModel = "spring", uses = { SubjectMapper.class })
public interface QuestionMapper extends EntityMapper<Question, Question> {
  @Mapping(target = "subject", source = "subject", qualifiedByName = "id")
  Question toDto(Question s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Question toDtoId(Question question);
}
