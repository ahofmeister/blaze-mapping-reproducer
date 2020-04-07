package org.acme.business.food.entity;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

@EntityView(FoodUnitMapping.class)
@UpdatableEntityView
public interface FoodUnitMappingCreate {

    FoodUnit getUnit();

    void setUnit(FoodUnit foodUnit);

    double getGramsPerUnit();

    void setGramsPerUnit(double gramsPerUnit);

}
