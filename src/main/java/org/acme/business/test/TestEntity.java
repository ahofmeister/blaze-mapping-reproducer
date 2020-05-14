package org.acme.business.test;

import javax.persistence.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexander Hofmeister
 */
@Entity
public class TestEntity {

  @Id
  public Long id;

  public String name;

  @PrePersist
  void prePersist() {
    if (id == null) {
      id = ThreadLocalRandom.current().nextLong(1000);
    }
  }

}
