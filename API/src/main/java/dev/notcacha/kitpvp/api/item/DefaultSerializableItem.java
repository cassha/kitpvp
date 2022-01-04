package dev.notcacha.kitpvp.api.item;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.beans.ConstructorProperties;
import java.util.Set;

@JsonSerialize(as = SerializableItem.class)
public class DefaultSerializableItem implements SerializableItem {

    private final String materialName;
    private final Short code;
    private final Integer amount;
    private final DecorationCompound decorationCompound;
    private final Set<EnchantmentCompound> enchantmentCompoundSet;

    @ConstructorProperties({
            "material_name", "code", "amount", "decoration", "enchantments"
    })
    public DefaultSerializableItem(String materialName, Short code, Integer amount, DecorationCompound decorationCompound, Set<EnchantmentCompound> enchantmentCompoundSet) {
        this.materialName = materialName;
        this.code = code;
        this.amount = amount;
        this.decorationCompound = decorationCompound;
        this.enchantmentCompoundSet = enchantmentCompoundSet;
    }

    public String getMaterialName() {
        return materialName;
    }

    public Short getCode() {
        return code;
    }

    public Integer getNumber() {
        return amount;
    }

    public DecorationCompound getDecoration() {
        return decorationCompound;
    }

    public Set<EnchantmentCompound> getEnchantments() {
        return enchantmentCompoundSet;
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(Material.matchMaterial(materialName), amount, code);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(getDecoration().getDisplayName());
        itemMeta.setLore(getDecoration().getDescription());

        for (EnchantmentCompound enchantmentCompound : getEnchantments()) {
            Enchantment type = Enchantment.getByName(enchantmentCompound.getType());
            if (type != null) {
                itemMeta.addEnchant(type, enchantmentCompound.getLevel(), true);
            }
        }

        item.setItemMeta(itemMeta);
        
        return item;
    }
}