package org.acme.business.food;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Alexander Hofmeister
 */
@EntityView(Ingredient.class)
public abstract class IngredientView {

  @Mapping("food.name")
  abstract String getJsonIgnorePackage();

  @Mapping("food.name")
  @JsonIgnore
  public abstract String getJsonIgnorePublic();

  @Mapping("food.name")
  @JsonIgnore
  protected abstract String getJsonIgnoreProtected();

  @Mapping("food.name")
  abstract String getPackageMod();

  @Mapping("food.name")
  abstract String getPublicMod();

  @Mapping("food.name")
  protected abstract String getProtectedMod();

}
