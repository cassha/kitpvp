package dev.notcacha.kitpvp.api.tag.applier;

import dev.notcacha.kitpvp.api.tag.Tag;
import org.bukkit.entity.Player;

public interface TagApplier {

    void apply(Player player, Tag tag);

}
