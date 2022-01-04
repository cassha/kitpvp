package dev.notcacha.kitpvp.core.item;

import dev.notcacha.kitpvp.api.item.DefaultSerializableItem;
import dev.notcacha.kitpvp.api.item.SerializableItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SerializableItemBuilder {

    protected String material = null;
    protected Short code = 0;
    protected Integer amount = 1;
    protected String displayName = null;
    protected List<String> description = new ArrayList<>();
    protected Set<SerializableItem.EnchantmentCompound> enchantmentsSet = new HashSet<>();

    public SerializableItemBuilder setMaterial(String material) {
        this.material = material;

        return this;
    }

    public SerializableItemBuilder setCode(Short code) {
        this.code = code;

        return this;
    }

    public SerializableItemBuilder setAmount(Integer amount) {
        this.amount = amount;

        return this;
    }

    public SerializableItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;

        return this;
    }

    public SerializableItemBuilder setDescription(List<String> description) {
        this.description = description;

        return this;
    }

    public SerializableItemBuilder setEnchantments(Set<SerializableItem.EnchantmentCompound> enchantmentsSet) {
        this.enchantmentsSet = enchantmentsSet;

        return this;
    }

    public SerializableItem build() {
        return new DefaultSerializableItem(
                material,
                code,
                amount,
                new SerializableItem.DecorationCompound() {
                    @Override
                    public String getDisplayName() {
                        return displayName;
                    }

                    @Override
                    public List<String> getDescription() {
                        return description;
                    }
                },
                enchantmentsSet
        );
    }

    public static SerializableItemBuilder newBuilder() {
        return new SerializableItemBuilder();
    }
}