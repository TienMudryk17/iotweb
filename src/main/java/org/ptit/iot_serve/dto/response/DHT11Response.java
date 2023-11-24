package org.ptit.iot_serve.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iot_serve.entity.DHT11;

@Data
@NoArgsConstructor
public class DHT11Response {
  private Float temperature;
  private Float humidity;

  public static DHT11Response from(DHT11 dht11) {
    DHT11Response response = new DHT11Response();
    response.setHumidity(dht11.getHumidity());
    response.setTemperature(dht11.getTemperature());
    return response;
  }
}
