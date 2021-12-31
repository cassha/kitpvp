package dev.notcacha.kitpvp.core.spawn;

import org.bukkit.Location;

public class SpawnManager {

    private Location location;
    private boolean defaultTeleport;

    public SpawnManager(Location location) {
        this(location, false);
    }

    public SpawnManager(Location location, boolean defaultTeleport) {
        this.location = location;
        this.defaultTeleport = defaultTeleport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isPresent() {
        return  location != null;
    }

    public boolean isDefaultTeleport() {
        return defaultTeleport;
    }

    public void setDefaultTeleport(boolean defaultTeleport) {
        this.defaultTeleport = defaultTeleport;
    }
}
