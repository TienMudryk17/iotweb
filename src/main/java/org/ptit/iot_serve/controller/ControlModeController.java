package org.ptit.iot_serve.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iot_serve.dto.request.CreateControlModeRequest;
import org.ptit.iot_serve.dto.response.ControlModeResponse;
import org.ptit.iot_serve.service.ControlModeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/control-modes")
@Slf4j
public class ControlModeController {

  private final ControlModeService service;

  @PostMapping("/lamp")
  @ResponseStatus(HttpStatus.CREATED)
  public ControlModeResponse createLamp(@RequestBody CreateControlModeRequest request) {
    log.info("(createLamp)request : {}", request);
    return service.createLamp(request);
  }

  @GetMapping("/lamp")
  @ResponseStatus(HttpStatus.OK)
  public ControlModeResponse getLamp() {
    log.info("(getLamp)");
    return service.getLamp();
  }
}
