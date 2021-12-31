package dev.notcacha.kitpvp.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import me.yushust.inject.AbstractModule;

public class MapperModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).toProvider(
                () -> {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new MrBeanModule());
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    return mapper;
                }
        ).singleton();
    }
}
