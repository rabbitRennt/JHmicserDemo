package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.MessageUser;
import io.github.jhipster.application.service.dto.MessageUser;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageUser} and its DTO {@link MessageUser}.
 */
@Mapper(componentModel = "spring", uses = { MessageMapper.class })
public interface MessageUserMapper
  extends EntityMapper<MessageUser, MessageUser> {
  @Mapping(target = "message", source = "message", qualifiedByName = "id")
  MessageUser toDto(MessageUser s);
}
