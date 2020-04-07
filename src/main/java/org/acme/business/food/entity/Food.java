package org.acme.business.food.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Food {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public String name;

    @ElementCollection
    public Set<FoodUnitMapping> unitMappings = new HashSet<>();

}
