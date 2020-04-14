package org.acme.business.test;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(TestEntity.class)
public interface TestId {
  @IdMapping
  Long getId();

}
