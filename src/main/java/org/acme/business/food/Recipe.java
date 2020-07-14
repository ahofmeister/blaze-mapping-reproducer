package org.acme.business.food;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexander Hofmeister
 */
@Entity
public class Recipe {

  @Id
  public Long id;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe", cascade = CascadeType.ALL)
  public final Set<Ingredient> ingredients;

  public Recipe() {
    this.ingredients = new HashSet<>();
  }

  @PrePersist
  void prePersist() {
    if (id == null) {
      id = ThreadLocalRandom.current().nextLong(1000);
    }
  }

}
