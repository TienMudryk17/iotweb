package org.ptit.iot_serve.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iot_serve.entity.Light;

@Data
@NoArgsConstructor
public class LightResponse {
  private Integer digitalRead;

  public static LightResponse from(Light light) {
    LightResponse response = new LightResponse();
    response.setDigitalRead(light.getDigitalRead());
    return response;
  }
}
