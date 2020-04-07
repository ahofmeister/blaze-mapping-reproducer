package org.acme.business.food.boundary;

import org.acme.business.food.entity.FoodCreate;
import org.acme.business.food.entity.FoodRead;
import com.blazebit.persistence.view.EntityViewManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class FoodService {

    @Inject
    protected EntityViewManager entityViewManager;

    @Inject
    protected EntityManager entityManager;

    public FoodRead save(FoodCreate entityView, boolean convert) {
        entityViewManager.save(entityManager, entityView);
        return convert ? entityViewManager.convert(entityView, FoodRead.class)
                : entityViewManager.find(entityManager, FoodRead.class,
                entityView.getId());
    }


}
