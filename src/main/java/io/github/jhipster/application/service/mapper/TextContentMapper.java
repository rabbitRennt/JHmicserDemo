package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.TextContent;
import io.github.jhipster.application.service.dto.TextContent;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TextContent} and its DTO {@link TextContent}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TextContentMapper
  extends EntityMapper<TextContent, TextContent> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  TextContent toDtoId(TextContent textContent);
}
