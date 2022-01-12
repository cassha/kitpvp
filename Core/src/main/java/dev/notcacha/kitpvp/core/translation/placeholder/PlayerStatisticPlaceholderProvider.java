package dev.notcacha.kitpvp.core.translation.placeholder;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.user.User;
import me.yushust.message.format.PlaceholderProvider;
import me.yushust.message.track.ContextRepository;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class PlayerStatisticPlaceholderProvider implements PlaceholderProvider<Player> {

    private final ModelRepository<User> userModelRepository;

    public PlayerStatisticPlaceholderProvider(ModelRepository<User> userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public @Nullable Object replace(ContextRepository contextRepository, Player player, String text) {
        User user = userModelRepository.findOneSync(player.getUniqueId().toString()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("Not found data of user " + player.getName());
        }

        String result = "";

        switch (text.toLowerCase()) {

            case "name": {

                result = user.getUsername();

                break;
            }

            case "kills": {

                result = String.valueOf(user.getStatistics().getKillsStatistic().getAmount());

                break;
            }

            case "deaths": {

                result = String.valueOf(user.getStatistics().getDeathsStatistic().getAmount());

                break;
            }

        }

        return result;
    }
}
