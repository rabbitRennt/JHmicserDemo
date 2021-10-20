package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.Message;
import io.github.jhipster.application.service.dto.Message;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Message} and its DTO {@link Message}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageMapper extends EntityMapper<Message, Message> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Message toDtoId(Message message);
}
