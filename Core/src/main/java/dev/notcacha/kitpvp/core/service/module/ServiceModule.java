package dev.notcacha.kitpvp.core.service.module;

import dev.notcacha.kitpvp.core.service.*;
import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.service.Service;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Service.class).named("spawn").to(SpawnService.class).singleton();
        bind(Service.class).named("command").to(CommandService.class).singleton();
        bind(Service.class).named("event").to(EventService.class).singleton();
        bind(Service.class).named("save").to(SaveOnStopService.class).singleton();
        bind(Service.class).named("hook-register").to(HookRegisterService.class).singleton();

        bind(Service.class).to(KitPvPService.class).singleton();
    }
}
