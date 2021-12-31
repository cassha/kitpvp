package dev.notcacha.kitpvp.core.service.module;

import dev.notcacha.kitpvp.core.service.CommandService;
import dev.notcacha.kitpvp.core.service.EventService;
import dev.notcacha.kitpvp.core.service.KitPvPService;
import dev.notcacha.kitpvp.core.service.SaveOnStopService;
import dev.notcacha.kitpvp.core.service.SpawnService;
import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.service.Service;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Service.class).named("spawn").to(SpawnService.class).singleton();
        bind(Service.class).named("command").to(CommandService.class).singleton();
        bind(Service.class).named("event").to(EventService.class).singleton();
        bind(Service.class).named("save").to(SaveOnStopService.class).singleton();

        bind(Service.class).to(KitPvPService.class).singleton();
    }
}
