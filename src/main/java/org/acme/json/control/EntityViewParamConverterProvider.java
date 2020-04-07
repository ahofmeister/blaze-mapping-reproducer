package org.acme.json.control;

import com.blazebit.persistence.integration.jackson.EntityViewAwareObjectMapper;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author Christian Beikov
 * 
 * @since 1.4.0
 */
@Provider
public class EntityViewParamConverterProvider
    implements ParamConverterProvider {

  @Inject
  Instance<EntityViewManager> entityViewManager;
  private volatile EntityViewAwareObjectMapper entityViewAwareObjectMapper;

  @Override
  @SuppressWarnings("unchecked")
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type type,
      Annotation[] annotations) {
    if (!entityViewManager.isUnsatisfied()
        && rawType.isAnnotationPresent(EntityView.class)) {
      return (ParamConverter<T>) new EntityViewParamConverter(this, rawType);
    }
    return null;
  }

  public ObjectReader readerFor(Class<?> rawType) {
    EntityViewAwareObjectMapper entityViewAwareObjectMapper =
        this.entityViewAwareObjectMapper;
    if (entityViewAwareObjectMapper == null) {
      synchronized (this) {
        entityViewAwareObjectMapper = this.entityViewAwareObjectMapper;
        if (entityViewAwareObjectMapper == null) {
          if (entityViewManager.isUnsatisfied()) {
            this.entityViewAwareObjectMapper =
                entityViewAwareObjectMapper = null;
          } else {
            this.entityViewAwareObjectMapper =
                entityViewAwareObjectMapper = new EntityViewAwareObjectMapper(
                    entityViewManager.get(), new ObjectMapper());
          }
        }
      }
    }
    return entityViewAwareObjectMapper.readerFor(rawType);
  }
}
