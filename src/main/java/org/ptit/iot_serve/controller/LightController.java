package org.ptit.iot_serve.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iot_serve.dto.request.CreateLightRequest;
import org.ptit.iot_serve.dto.response.LightResponse;
import org.ptit.iot_serve.service.LightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lights")
@Slf4j
public class LightController {

  private final LightService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LightResponse create(@RequestBody CreateLightRequest request) {
    log.info("(create)request : {}", request);
    return service.create(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public LightResponse get() {
    log.info("(get)");
    return service.get();
  }
}
