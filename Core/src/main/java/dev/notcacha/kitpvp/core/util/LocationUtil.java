package dev.notcacha.kitpvp.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public class LocationUtil {

    /**
     * @return {@param location} in string format
     */

    public static String toString(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location is null!");
        }

        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ();
    }

    /**
     * @return {@param location} in bukkit location format
     * */

    @Nullable
    public static Location ofString(String location) {
        if (location == null) {
            return null;
        }

        String[] locationPart = location.split(";");

        if (locationPart.length < 4) {
            return null;
        }

        return new Location(Bukkit.getWorld(locationPart[0]),
                Double.parseDouble(locationPart[1]),
                Double.parseDouble(locationPart[2]),
                Double.parseDouble(locationPart[3]));
    }
}
