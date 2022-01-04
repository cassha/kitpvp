package dev.notcacha.kitpvp.api.kit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.model.Model;

import java.util.List;
import java.util.Map;

@JsonDeserialize(as = DefaultKit.class)
public interface Kit extends Model {

    /**
     * @return The display name from kit.
     */

    @JsonProperty("display_name")
    String getDisplayName();

    /**
     * @return The description from kit.
     */

    @JsonProperty("description")
    List<String> getDescription();

    /**
     * @return The icon from kit in {@link SerializableItem} format.
     */

    @JsonProperty("icon")
    SerializableItem getIcon();

    /**
     * @return All items in the kit inventory in map format
     */

    @JsonProperty("inventory_contents")
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

    @JsonProperty("armor_contents")
    Map<Integer, SerializableItem> getArmorContents();

    /**
     * Set new armor contents from kit.
     * @param armorContents new contents has been set.
     */

    void setArmorContents(Map<Integer, SerializableItem> armorContents);

}
