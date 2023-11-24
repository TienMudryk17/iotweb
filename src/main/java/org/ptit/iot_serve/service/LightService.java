package org.ptit.iot_serve.service;


import org.ptit.iot_serve.dto.request.CreateLightRequest;
import org.ptit.iot_serve.dto.response.LightResponse;

public interface LightService {
  LightResponse create(CreateLightRequest request);
  LightResponse get();
  void delete();
}
