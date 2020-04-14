package org.acme.business.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Alexander Hofmeister
 */
@Entity
public class TestEntity {

  @Id
  @GeneratedValue
  public Long id;

  public String name;

  @OneToOne
  public TestEntity user;

}
