package dev.notcacha.kitpvp.api.kit;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.notcacha.kitpvp.api.item.SerializableItem;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Map;

@JsonSerialize(as = Kit.class)
public class DefaultKit implements Kit {

    private final String id;
    private String displayName;
    private List<String> description;
    private SerializableItem icon;
    private Map<Integer, SerializableItem> inventoryContents;
    private Map<Integer, SerializableItem> armorContents;

    @ConstructorProperties({
            "_id", "display_name", "description", "icon", "inventory_contents", "armor_contents"
    })
    public DefaultKit(String id, String displayName, List<String> description, SerializableItem icon, Map<Integer, SerializableItem> inventoryContents, Map<Integer, SerializableItem> armorContents) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.icon = icon;
        this.inventoryContents = inventoryContents;
        this.armorContents = armorContents;
    }


    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setDescription(List<String> description) {
        this.description = description;
    }

    @Override
    public void setIcon(SerializableItem icon) {
        this.icon = icon;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public SerializableItem getIcon() {
        return icon;
    }

    @Override
    public Map<Integer, SerializableItem> getInventoryContents() {
        return inventoryContents;
    }

    @Override
    public void setInventoryContents(Map<Integer, SerializableItem> inventoryContents) {
        this.inventoryContents = inventoryContents;
    }

    @Override
    public Map<Integer, SerializableItem> getArmorContents() {
        return armorContents;
    }

    @Override
    public void setArmorContents(Map<Integer, SerializableItem> armorContents) {
        this.armorContents = armorContents;
    }

    @Override
    public String getId() {
        return id;
    }
}
