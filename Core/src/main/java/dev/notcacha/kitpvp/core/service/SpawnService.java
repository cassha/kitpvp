package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import javax.inject.Named;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.file.YamlFile;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import dev.notcacha.kitpvp.core.util.LocationUtil;

public class SpawnService implements Service {

    @Inject private SpawnManager spawnManager;
    @Inject @Named("spawn") private YamlFile spawnFile;

    @Override
    public void start() {
        if (!spawnFile.contains("spawn")) return;

        spawnManager.setLocation(
                LocationUtil.ofString(spawnFile.getString("spawn"))
        );
        spawnManager.setDefaultTeleport(spawnFile.getBoolean("default-teleport"));
    }

    @Override
    public void stop() {
        if (!spawnManager.isPresent()) {
            return;
        }

        spawnFile.set("spawn", LocationUtil.toString(spawnManager.getLocation()));

        spawnFile.set("default-teleport", spawnManager.isDefaultTeleport());
        spawnFile.save();
    }
}
