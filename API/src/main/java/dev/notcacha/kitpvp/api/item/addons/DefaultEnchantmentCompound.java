package dev.notcacha.kitpvp.api.item.addons;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.notcacha.kitpvp.api.item.SerializableItem;

import java.beans.ConstructorProperties;

@JsonSerialize(as = SerializableItem.EnchantmentCompound.class)
public class DefaultEnchantmentCompound implements SerializableItem.EnchantmentCompound {

    private final String type;
    private final int level;

    @ConstructorProperties({
            "type", "level"
    })
    public DefaultEnchantmentCompound(String type, int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
