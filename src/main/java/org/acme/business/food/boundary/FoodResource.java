package org.acme.business.food.boundary;

import org.acme.business.food.entity.FoodCreate;
import org.acme.business.food.entity.FoodRead;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("foods")
@Produces(MediaType.APPLICATION_JSON)
public class FoodResource {

    @Inject
    FoodService foodService;

    @POST
    @Transactional
    public FoodRead add(FoodCreate food) {
        System.out.println("--------------------------------");
        System.out.println(food.getUnitMappings().size());
        if (food.getUnitMappings().size() >= 1) {
            food.getUnitMappings().forEach(System.out::println);
        }
        System.out.println("--------------------------------");
        return this.foodService.save(food, true);
    }


}
