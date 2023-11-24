package org.ptit.iot_serve.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iot_serve.service.ControlModeService;
import org.ptit.iot_serve.service.DHT11Service;
import org.ptit.iot_serve.service.LightService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class DeleteTableScheduler {
  private final ControlModeService controlModeService;
  private final DHT11Service dht11Service;
  private final LightService lightService;
  @Value(value = "${application.sensor_delete.enable:true}")
  private Boolean enable;
  @Scheduled(fixedRate = 180000)
  @Transactional
  public void executeDHT11() {
    log.info("(execute)enable : {}", enable);
    if (!enable) {
      return;
    }
    try {
      dht11Service.delete();
    } catch (Exception exception) {
      log.error("exception : {}", exception.getClass().getName());
    }
  }
  @Scheduled(fixedRate = 180000)
  @Transactional
  public void executeLight() {
    log.info("(execute)enable : {}", enable);
    if (!enable) {
      return;
    }
    try {
      lightService.delete();
    } catch (Exception exception) {
      log.error("exception : {}", exception.getClass().getName());
    }
  }
}
