package org.acme.business.food.entity;

import com.blazebit.persistence.view.EntityView;

@EntityView(FoodUnitMapping.class)
public interface FoodUnitMappingView {

    FoodUnit getUnit();

    double getGramsPerUnit();

}
