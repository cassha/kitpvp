package dev.notcacha.kitpvp.api.kit;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.model.Model;

import java.util.List;
import java.util.Map;

public interface Kit extends Model {

    /**
     * @return The display name from kit.
     */

    String getDisplayName();

    /**
     * @return The description from kit.
     */

    List<String> getDescription();

    /**
     * @return The icon from kit in {@link SerializableItem} format.
     */

    SerializableItem getIcon();

    /**
     * @return All items in the kit inventory in map format
     */

    Map<Integer, SerializableItem> getInventoryContents();

    /**
     * Set new inventory contents from kit.
     * @param inventoryContents new contents has been set.
     */

    void setInventoryContents(Map<Integer, SerializableItem> inventoryContents);

    /**
     * Get armor items in map.
     *
     * @return The contents from armor.
     */

    Map<Integer, SerializableItem> getArmorContents();

    /**
     * Set new armor contents from kit.
     * @param armorContents new contents has been set.
     */

    void setArmorContents(Map<Integer, SerializableItem> armorContents);

}
