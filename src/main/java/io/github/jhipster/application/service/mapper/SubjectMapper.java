package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.Subject;
import io.github.jhipster.application.service.dto.Subject;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subject} and its DTO {@link Subject}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubjectMapper extends EntityMapper<Subject, Subject> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Subject toDtoId(Subject subject);
}
