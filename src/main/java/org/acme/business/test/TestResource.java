package org.acme.business.test;

import com.blazebit.persistence.view.EntityViewManager;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Alexander Hofmeister
 */
@Path("test")
public class TestResource {

  @Inject
  EntityManager entityManager;

  @Inject
  EntityViewManager entityViewManager;

  @POST
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  public TestCreate add(TestCreate view) {
    System.out.println(view.getId()); // why null?
    entityViewManager.save(entityManager, view);
    return view;
  }

}
