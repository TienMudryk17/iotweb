package org.ptit.iot_serve.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  private String id;

  @PrePersist
  public void ensureId() {
    this.id = Objects.isNull(this.id) ? UUID.randomUUID().toString() : this.id;
  }
}
