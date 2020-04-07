package org.acme.json.control;

import com.blazebit.persistence.view.EntityViewManager;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author Christian Beikov
 * 
 * @since 1.4.0
 */
@Provider
@Consumes({ "application/json", "application/*+json", "text/json" })
public class EntityViewMessageBodyReader implements MessageBodyReader<Object> {

  @Inject
  Instance<EntityViewManager> entityViewManager;

  @Inject
  EntityViewParamConverterProvider paramConverterProvider;

  @Override
  public boolean isReadable(Class<?> type, Type genericType,
      Annotation[] annotations, MediaType mediaType) {
    if (entityViewManager.isUnsatisfied()
        || entityViewManager.get().getMetamodel().view(type) == null) {
      return false;
    }
    if (InputStream.class.isAssignableFrom(type)
        || Reader.class.isAssignableFrom(type)) {
      return false;
    }
    String subtype;
    return (subtype = mediaType.getSubtype()) == null
        || "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json")
        || "javascript".equals(subtype) || "x-javascript".equals(subtype)
        || "x-json".equals(subtype);
  }

  @Override
  public Object readFrom(Class<Object> type, Type genericType,
      Annotation[] annotations, MediaType mediaType,
      MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
      throws IOException, WebApplicationException {
    ParamConverter<Object> converter =
        paramConverterProvider.getConverter(type, genericType, annotations);
    if (converter instanceof EntityViewParamConverter) {
      return ((EntityViewParamConverter) converter)
          .fromInputStream(entityStream);
    }
    InputStreamReader inputStreamReader =
        new InputStreamReader(entityStream, StandardCharsets.UTF_8);
    StringBuilder sb = new StringBuilder();
    char[] arr = new char[8 * 1024];
    int read;
    while ((read = inputStreamReader.read(arr, 0, arr.length)) != -1) {
      sb.append(arr, 0, read);
    }
    return converter.fromString(sb.toString());
  }
}
