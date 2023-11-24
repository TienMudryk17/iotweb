package org.ptit.iot_serve.service;

import org.ptit.iot_serve.dto.request.CreateDHT11Request;
import org.ptit.iot_serve.dto.response.DHT11Response;

public interface DHT11Service {
  DHT11Response create(CreateDHT11Request request);
  DHT11Response get();
  void delete();
}
