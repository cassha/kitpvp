package dev.notcacha.kitpvp.core.kit;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.kit.applier.KitApplier;
import dev.notcacha.kitpvp.core.util.PlayerInventoryUtil;
import org.bukkit.entity.Player;

import java.util.Map;

public class DefaultKitApplier implements KitApplier {

    @Override
    public void apply(Player player, Kit kit) {
        PlayerInventoryUtil.clear(player);

        kit.getInventoryContents().forEach((integer, item) -> {
            player.getInventory().setItem(integer, item.toItemStack());
        });

        Map<Integer, SerializableItem> map = kit.getArmorContents();

        if (map.containsKey(0)) {
            player.getInventory().setHelmet(map.get(0).toItemStack());
        }
        if (map.containsKey(1)) {
            player.getInventory().setChestplate(map.get(1).toItemStack());
        }
        if (map.containsKey(2)) {
            player.getInventory().setLeggings(map.get(2).toItemStack());
        }
        if (map.containsKey(3)) {
            player.getInventory().setBoots(map.get(3).toItemStack());
        }


    }

}
