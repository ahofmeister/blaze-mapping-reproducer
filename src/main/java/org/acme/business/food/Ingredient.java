package org.acme.business.food;

import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * @author Alexander Hofmeister
 */
@Entity
public class Ingredient {

  @Id
  public Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @NotNull
  public Food food;

  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  public Recipe recipe;

  public Ingredient(@NotNull Food food, @NotNull Recipe recipe) {
    this.food = food;
    this.recipe = recipe;
  }

  public Ingredient() {
  }
  @PrePersist
  void prePersist() {
    if (id == null) {
      id = ThreadLocalRandom.current().nextLong(1000);
    }
  }

}
