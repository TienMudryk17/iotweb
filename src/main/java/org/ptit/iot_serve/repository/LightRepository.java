package org.ptit.iot_serve.repository;

import java.util.Optional;
import org.ptit.iot_serve.entity.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LightRepository extends JpaRepository<Light, String> {
  @Query(nativeQuery = true, value = "select * from light order by created_at desc limit 1")
  Optional<Light> get();

  @Query(
      nativeQuery = true,
      value = "DELETE FROM light WHERE created_at NOT IN (SELECT max(ls.created_at) FROM (select * from light) as ls)")
  @Modifying
  void delete();
}
