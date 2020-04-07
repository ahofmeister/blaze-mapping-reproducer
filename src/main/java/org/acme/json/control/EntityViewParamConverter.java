package org.acme.json.control;


import javax.ws.rs.ext.ParamConverter;
import java.io.IOException;
import java.io.InputStream;


public class EntityViewParamConverter implements ParamConverter<Object> {
    private final EntityViewParamConverterProvider provider;
    private final Class<?> rawType;

    public EntityViewParamConverter(EntityViewParamConverterProvider provider,
                                    Class<?> rawType) {
        this.provider = provider;
        this.rawType = rawType;
    }

    public Object fromInputStream(InputStream value) throws IOException {
        return provider.readerFor(rawType).readValue(value);
    }

    @Override
    public Object fromString(String value) {
        try {
            return provider.readerFor(rawType).readValue(value);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString(Object value) {
        throw new UnsupportedOperationException();
    }
}
