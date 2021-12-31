package dev.notcacha.kitpvp.core.item;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class CoreSerializableItem implements SerializableItem {

    private final String materialName;
    private final int materialCode;
    private final Short code;
    private final Integer amount;
    private final DecorationCompound decorationCompound;
    private final Set<EnchantmentCompound> enchantmentCompoundSet;

    public CoreSerializableItem(String materialName, int materialCode, Short code, Integer amount, DecorationCompound decorationCompound, Set<EnchantmentCompound> enchantmentCompoundSet) {
        this.materialName = materialName;
        this.materialCode = materialCode;
        this.code = code;
        this.amount = amount;
        this.decorationCompound = decorationCompound;
        this.enchantmentCompoundSet = enchantmentCompoundSet;
    }

    public String getMaterialName() {
        return materialName;
    }

    public int getMaterialCode() {
        return materialCode;
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
        ItemStack item = null;

        if (materialName == null) {
            item = new ItemStack(materialCode, amount, code);
        } else {
            item = new ItemStack(Material.matchMaterial(materialName), amount, code);
        }

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