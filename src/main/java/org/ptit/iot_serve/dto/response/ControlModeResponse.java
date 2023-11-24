package org.ptit.iot_serve.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iot_serve.constants.ManualMode;
import org.ptit.iot_serve.constants.OperationMode;
import org.ptit.iot_serve.entity.ControlMode;

@Data
@NoArgsConstructor
public class ControlModeResponse {
  private OperationMode operationMode;
  private ManualMode manualMode;

  public static ControlModeResponse from(ControlMode controlMode) {
    ControlModeResponse response = new ControlModeResponse();
    response.setOperationMode(controlMode.getOperationMode());
    response.setManualMode(controlMode.getManualMode());
    return response;
  }
}
