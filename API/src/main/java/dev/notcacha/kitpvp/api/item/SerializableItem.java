package dev.notcacha.kitpvp.api.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.notcacha.kitpvp.api.item.addons.DefaultDecorationCompound;
import dev.notcacha.kitpvp.api.item.addons.DefaultEnchantmentCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Item that can be built to an {@link org.bukkit.inventory.ItemStack}
 */

@JsonDeserialize(as = DefaultSerializableItem.class)
public interface SerializableItem {

    /**
     * @return material of the item.
     */

    @JsonProperty("material_name")
    String getMaterialName();

    /**
     * @return material code
     */

    @JsonProperty("code")
    Short getCode();

    /**
     * @return item quantity
     */

    @JsonProperty("amount")
    Integer getNumber();

    /**
     * @return The item decoration.
     */

    @JsonProperty("decoration")
    DecorationCompound getDecoration();

    @JsonDeserialize(as = DefaultDecorationCompound.class)
    interface DecorationCompound {

        /**
         * @return The display name from item.
         */

        @JsonProperty("display_name")
        String getDisplayName();

        /**
         * @return The description from item.
         */

        @JsonProperty("description")
        List<String> getDescription();

    }

    /**
     * @return enchantments of the item
     */

    @JsonProperty("enchantments")
    Set<EnchantmentCompound> getEnchantments();

    @JsonDeserialize(as = DefaultEnchantmentCompound.class)
    interface EnchantmentCompound {

        /**
         * @return enchantment to be applied
         */

        @JsonProperty("type")
        String getType();

        /**
         * @return level of the enchantment
         */

        @JsonProperty("level")
        int getLevel();

    }

    @JsonIgnore
    default ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(Material.matchMaterial(getMaterialName()), getNumber());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(
                ChatColor.translateAlternateColorCodes(
                        '&',
                        getDecoration().getDisplayName()
                )
        );

        List<String> lore = getDecoration().getDescription();
        lore.replaceAll(message -> message.replace(message, ChatColor.translateAlternateColorCodes('&', message)));

        itemMeta.setLore(
                lore
        );

        for (EnchantmentCompound enchantmentCompound : getEnchantments()) {
            Enchantment type = Enchantment.getByName(enchantmentCompound.getType());
            if (type != null) {
                itemMeta.addEnchant(type, enchantmentCompound.getLevel(), true);
            }
        }

        return itemStack;
    }

    static SerializableItem fromItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        return new SerializableItem() {
            @Override
            public String getMaterialName() {
                return itemStack.getType().name();
            }

            @Override
            public Short getCode() {
                return itemStack.getDurability();
            }

            @Override
            public Integer getNumber() {
                return itemStack.getAmount();
            }

            @Override
            public DecorationCompound getDecoration() {
                return new DecorationCompound() {
                    @Override
                    public String getDisplayName() {
                        return itemMeta.getDisplayName();
                    }

                    @Override
                    public List<String> getDescription() {
                        return itemMeta.getLore();
                    }
                };
            }

            @Override
            public Set<EnchantmentCompound> getEnchantments() {
                Set<EnchantmentCompound> enchantmentCompoundSet = new HashSet<>();

                itemMeta.getEnchants().forEach((enchantment, level) -> enchantmentCompoundSet.add(new EnchantmentCompound() {
                    @Override
                    public String getType() {
                        return enchantment.getName();
                    }

                    @Override
                    public int getLevel() {
                        return level;
                    }
                }));

                return enchantmentCompoundSet;
            }

        };
    }

}
