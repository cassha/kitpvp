package dev.notcacha.kitpvp.core.kit;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.core.util.SerializableItemDeserializerUtil;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultKit implements Kit {

    private final String id;
    private final String displayName;
    private final List<String> description;
    private final SerializableItem icon;
    private Map<Integer, SerializableItem> inventoryContents;
    private Map<Integer, SerializableItem> armorContents;

    public DefaultKit(Map<String, Object> serializedKit) {
        this(
                (String) serializedKit.get("id"),
                (String) serializedKit.get("display_name"),
                (List<String>) serializedKit.get("description"),
                SerializableItem.fromItemStack((ItemStack) serializedKit.get("icon")),
                SerializableItemDeserializerUtil.fromItemStack((Map<Integer, ItemStack>) serializedKit.get("inventory_contents")),
                SerializableItemDeserializerUtil.fromItemStack((Map<Integer, ItemStack>) serializedKit.get("armor_contents"))
        );
    }

    public DefaultKit(String id, String displayName, List<String> description, SerializableItem icon, Map<Integer, SerializableItem> inventoryContents, Map<Integer, SerializableItem> armorContents) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.icon = icon;
        this.inventoryContents = inventoryContents;
        this.armorContents = armorContents;
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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();

        serialized.put("id", id);
        serialized.put("display_name", displayName);
        serialized.put("description", description);
        serialized.put("icon", icon.toItemStack());
        serialized.put("inventory_contents", SerializableItemDeserializerUtil.toItemStack(inventoryContents));
        serialized.put("armor_contents", SerializableItemDeserializerUtil.toItemStack(armorContents));

        return serialized;
    }
}
