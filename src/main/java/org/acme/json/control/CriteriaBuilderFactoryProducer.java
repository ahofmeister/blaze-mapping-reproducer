package org.acme.json.control;

import com.blazebit.persistence.view.ConfigurationProperties;
import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import org.acme.business.test.TestCreate;
import org.acme.business.test.TestId;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@ApplicationScoped
public class CriteriaBuilderFactoryProducer {
  private volatile CriteriaBuilderFactory cbf;
  private volatile EntityViewManager evm;

  @Inject
  @Produces
  @ApplicationScoped
  public CriteriaBuilderFactory cbf(EntityManager entityManager) {
    return (CriteriaBuilderFactory) Proxy
        .newProxyInstance(CriteriaBuilderFactory.class.getClassLoader(),
            new Class[]{CriteriaBuilderFactory.class}, (proxy, method, args) -> {
              CriteriaBuilderFactory delegate = cbf;
              if (delegate == null) {
                synchronized (this) {
                  delegate = cbf;
                  if (delegate == null) {
                    delegate = cbf = Criteria.getDefault()
                        .createCriteriaBuilderFactory(entityManager.getEntityManagerFactory());
                  }
                }
              }
              return method.invoke(delegate, args);
            });
  }

  @Inject
  @Produces
  @ApplicationScoped
  public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf) {

    EntityViewConfiguration configuration = EntityViews.createDefaultConfiguration();

    configuration.addEntityView(TestCreate.class);
    configuration.addEntityView(TestId.class);

    configuration.setProperty(ConfigurationProperties.UPDATER_STRICT_CASCADING_CHECK, "false");


    return (EntityViewManager) Proxy.newProxyInstance(EntityViewManager.class.getClassLoader(),
        new Class[]{EntityViewManager.class}, (proxy, method, args) -> {
          EntityViewManager delegate = evm;
          if (delegate == null) {
            synchronized (this) {
              delegate = evm;
              if (delegate == null) {
                delegate = evm = configuration.createEntityViewManager(cbf);
              }
            }
          }
          return method.invoke(delegate, args);
        });
  }
}
