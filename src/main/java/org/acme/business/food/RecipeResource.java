package org.acme.business.food;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Alexander Hofmeister
 */
@Path("recipes")
public class RecipeResource {

  @Inject
  RecipeService recipeService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public List<RecipeView> add() {
    return recipeService.listAllForSearch();
  }

}
