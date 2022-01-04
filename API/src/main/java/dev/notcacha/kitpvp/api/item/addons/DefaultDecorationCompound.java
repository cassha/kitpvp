package dev.notcacha.kitpvp.api.item.addons;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.notcacha.kitpvp.api.item.SerializableItem;

import java.beans.ConstructorProperties;
import java.util.List;

@JsonSerialize(as = SerializableItem.DecorationCompound.class)
public class DefaultDecorationCompound implements SerializableItem.DecorationCompound {

    private final String displayName;
    private final List<String> description;

    @ConstructorProperties({
            "display_name", "description"
    })
    public DefaultDecorationCompound(String displayName, List<String> description) {
        this.displayName = displayName;
        this.description = description;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }
}
