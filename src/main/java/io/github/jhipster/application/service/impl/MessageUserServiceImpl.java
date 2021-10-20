package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.MessageUser;
import io.github.jhipster.application.repository.MessageUserRepository;
import io.github.jhipster.application.service.MessageUserService;
import io.github.jhipster.application.service.dto.MessageUser;
import io.github.jhipster.application.service.mapper.MessageUserMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MessageUser}.
 */
@Service
@Transactional
public class MessageUserServiceImpl implements MessageUserService {

  private final Logger log = LoggerFactory.getLogger(
    MessageUserServiceImpl.class
  );

  private final MessageUserRepository messageUserRepository;

  private final MessageUserMapper messageUserMapper;

  public MessageUserServiceImpl(
    MessageUserRepository messageUserRepository,
    MessageUserMapper messageUserMapper
  ) {
    this.messageUserRepository = messageUserRepository;
    this.messageUserMapper = messageUserMapper;
  }

  @Override
  public MessageUser save(MessageUser messageUser) {
    log.debug("Request to save MessageUser : {}", messageUser);
    MessageUser messageUser = messageUserMapper.toEntity(messageUser);
    messageUser = messageUserRepository.save(messageUser);
    return messageUserMapper.toDto(messageUser);
  }

  @Override
  public Optional<MessageUser> partialUpdate(MessageUser messageUser) {
    log.debug("Request to partially update MessageUser : {}", messageUser);

    return messageUserRepository
      .findById(messageUser.getId())
      .map(existingMessageUser -> {
        messageUserMapper.partialUpdate(existingMessageUser, messageUser);

        return existingMessageUser;
      })
      .map(messageUserRepository::save)
      .map(messageUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<MessageUser> findAll() {
    log.debug("Request to get all MessageUsers");
    return messageUserRepository
      .findAll()
      .stream()
      .map(messageUserMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<MessageUser> findOne(Long id) {
    log.debug("Request to get MessageUser : {}", id);
    return messageUserRepository.findById(id).map(messageUserMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete MessageUser : {}", id);
    messageUserRepository.deleteById(id);
  }
}
