package org.acme.business.food;

import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * @author Alexander Hofmeister
 */
@Entity
public class Food {

  @Id
  public Long id;

  public String name;

  public String description;

  public Food(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Food() {
  }

  @PrePersist
  void prePersist() {
    if (id == null) {
      id = ThreadLocalRandom.current().nextLong(1000);
    }
  }

}
