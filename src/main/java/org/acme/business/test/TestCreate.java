package org.acme.business.test;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.IdMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alexander Hofmeister
 */
@EntityView(TestEntity.class)
@CreatableEntityView
public interface TestCreate {

  @IdMapping
  Long getId();

  String getName();

  void setName(String name);

  void setId(Long id);

}
