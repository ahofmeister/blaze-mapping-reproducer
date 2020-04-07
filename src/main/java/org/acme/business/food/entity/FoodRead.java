package org.acme.business.food.entity;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import java.util.Set;

@EntityView(Food.class)
public interface FoodRead {

    @IdMapping
    Long getId();

    String getName();

    Set<FoodUnitMappingView> getUnitMappings();

}
