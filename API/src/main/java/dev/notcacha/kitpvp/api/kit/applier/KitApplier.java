package dev.notcacha.kitpvp.api.kit.applier;

import dev.notcacha.kitpvp.api.kit.Kit;
import org.bukkit.entity.Player;

public interface KitApplier {

    /**
     * Apply the kit.
     * @param player holder has been applied.
     * @param kit has been applied.
     */

    void apply(Player player, Kit kit);
}
