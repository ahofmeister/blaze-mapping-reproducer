package org.acme.business.food.entity;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Embeddable
public class FoodUnitMapping {

    @Min(1)
    public double gramsPerUnit;

    @Enumerated(EnumType.STRING)
    @NotNull
    public FoodUnit unit;

}
