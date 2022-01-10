package dev.notcacha.kitpvp.core.translation;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.user.User;
import me.yushust.message.language.Linguist;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.logging.Level;

public class DefaultLanguageProvider implements Linguist<Player> {

    @Inject private ModelRepository<User> userModelRepository;
    @Inject private Plugin plugin;

    @Override
    public @Nullable String getLanguage(Player player) {
        User user = userModelRepository.findOneSync(player.getUniqueId().toString()).orElse(null);

        if (user == null) {
            plugin.getLogger().log(Level.SEVERE, "Error while finding user data for " + player.getName());

            return null;
        }

        return user.getLanguage();
    }
}
