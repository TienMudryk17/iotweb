package org.ptit.iot_serve.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iot_serve.constants.Device;
import org.ptit.iot_serve.dto.request.CreateControlModeRequest;
import org.ptit.iot_serve.dto.response.ControlModeResponse;
import org.ptit.iot_serve.entity.ControlMode;
import org.ptit.iot_serve.repository.ControlModeRepository;
import org.ptit.iot_serve.service.ControlModeService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class ControlModeServiceImpl implements ControlModeService {

  private final ControlModeRepository repository;

  @Override
  @Transactional
  public ControlModeResponse createLamp(CreateControlModeRequest request) {
    log.info("(createLamp)request : {}", request);
    ControlMode controlMode = request.toControlMode();
    controlMode.setDevice(Device.LAMP);
    return ControlModeResponse.from(repository.save(controlMode));
  }

  @Override
  @Transactional(readOnly = true)
  public ControlModeResponse getLamp() {
    log.info("(getLamp)");
    return repository
        .get(Device.LAMP.name())
        .map(ControlModeResponse::from)
        .orElse(new ControlModeResponse());
  }
}
