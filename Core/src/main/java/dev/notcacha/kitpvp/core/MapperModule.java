package dev.notcacha.kitpvp.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.javatic.mongo.jacksonCodec.ObjectMapperFactory;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;

import javax.inject.Singleton;

public class MapperModule extends AbstractModule {

    @Override
    protected void configure() {}

    @Singleton
    @Provides
    public ObjectMapper provideObjectMapper() {
        return ObjectMapperFactory.createObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
