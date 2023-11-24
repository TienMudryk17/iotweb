package org.ptit.iot_serve.repository;

import java.util.Optional;
import org.ptit.iot_serve.entity.ControlMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ControlModeRepository extends JpaRepository<ControlMode, String> {
  @Query(
      nativeQuery = true,
      value = "select * from control_mode where device = :device order by created_at desc limit 1")
  Optional<ControlMode> get(@Param("device") String device);

  @Query(
      nativeQuery = true,
      value =
          "DELETE FROM control_mode WHERE created_at NOT IN (SELECT max(cms.created_at) FROM (select * from control_mode) as cms)")
  @Modifying
  void delete();
}
