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
public abstract class TestCreate {

  @IdMapping
  @JsonIgnore // not ignored?
  abstract Long getId();

  // no setter, but I can create it?
  abstract String getName();

  @JsonIgnore// OK
  String getS() {
    return getName() + "";
  }

  abstract EntityViewManager evm();

  @JsonIgnore // not ignored
  protected abstract TestId getUser();

  @JsonIgnore // it is ignored due processing,
  // but I would like to have an error for unknown attribute?
  protected abstract void setUser(TestId user);

  // OK
  public Long getUserId() {
    return getUser() == null ? null : getUser().getId();
  }

  // OK
  protected void setUserId(Long userId) {
    setUser(userId == null ? null : evm().getReference(TestId.class, userId));
  }

}
