package org.ptit.iot_serve.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iot_serve.dto.request.CreateLightRequest;
import org.ptit.iot_serve.dto.response.LightResponse;
import org.ptit.iot_serve.repository.LightRepository;
import org.ptit.iot_serve.service.LightService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class LightServiceImpl implements LightService {
  private final LightRepository repository;

  @Override
  @Transactional
  public LightResponse create(CreateLightRequest request) {
    log.info("(create)request : {}", request);
    return LightResponse.from(repository.save(request.toLight()));
  }

  @Override
  @Transactional(readOnly = true)
  public LightResponse get() {
    log.info("(get)");
    return repository.get().map(LightResponse::from).orElse(new LightResponse());
  }

  @Override
  @Transactional
  public void delete() {
    log.info("(delete)");
    repository.delete();
  }
}
