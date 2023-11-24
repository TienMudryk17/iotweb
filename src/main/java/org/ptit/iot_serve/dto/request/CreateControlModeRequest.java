package org.ptit.iot_serve.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iot_serve.constants.ManualMode;
import org.ptit.iot_serve.constants.OperationMode;
import org.ptit.iot_serve.entity.ControlMode;

@Data
@NoArgsConstructor
public class CreateControlModeRequest {
  private OperationMode operationMode;
  private ManualMode manualMode;

  public ControlMode toControlMode() {
    ControlMode controlMode = new ControlMode();
    controlMode.setOperationMode(this.getOperationMode());
    controlMode.setManualMode(this.getManualMode());
    return controlMode;
  }
}
