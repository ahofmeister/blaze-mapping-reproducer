package org.acme.business.food;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Alexander Hofmeister
 */
@ApplicationScoped
public class RecipeService {

  @Inject
  EntityViewManager entityViewManager;

  @Inject
  EntityManager entityManager;

  @Inject
  CriteriaBuilderFactory factory;

  List<RecipeView> listAllForSearch() {

    addRecipe();
    CriteriaBuilder<Recipe> cb = factory.create(entityManager, Recipe.class);
    return entityViewManager.applySetting(EntityViewSetting.create(RecipeView.class), cb)
        .getResultList();
  }

  private void addRecipe() {
    Recipe recipe = new Recipe();
    recipe.ingredients.add(new Ingredient(new Food("banana", "a banna"), recipe));
    recipe.ingredients.add(new Ingredient(new Food("apple", "a apple"), recipe));

    entityManager.persist(recipe);
  }

}
