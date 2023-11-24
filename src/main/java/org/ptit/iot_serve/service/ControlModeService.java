package org.ptit.iot_serve.service;


import org.ptit.iot_serve.dto.request.CreateControlModeRequest;
import org.ptit.iot_serve.dto.response.ControlModeResponse;

public interface ControlModeService {
  ControlModeResponse createLamp(CreateControlModeRequest request);
  ControlModeResponse getLamp();
}
