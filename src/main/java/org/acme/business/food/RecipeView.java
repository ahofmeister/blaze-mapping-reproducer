package org.acme.business.food;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import java.util.Set;

@EntityView(Recipe.class)
public interface RecipeView {

  @IdMapping
  Long getId();

  Set<IngredientView> getIngredients();

}
