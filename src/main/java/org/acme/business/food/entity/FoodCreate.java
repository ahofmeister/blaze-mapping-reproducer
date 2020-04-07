package org.acme.business.food.entity;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableMapping;

import java.util.Set;

@EntityView(Food.class)
@CreatableEntityView
public interface FoodCreate {

    @IdMapping
    Long getId();

    void setName(String name);

    String getName();

    @UpdatableMapping(orphanRemoval = true)
    Set<FoodUnitMappingCreate> getUnitMappings();

}
